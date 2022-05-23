package com.example.booklify.profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.booklify.R;
import com.example.booklify.model.BookModel;
import com.example.booklify.model.UserModel;
import com.example.booklify.response.BookResponse;
import com.example.booklify.viewmodels.BookListViewModel;
import com.example.booklify.viewmodels.UserListViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {

    private TextView changeImage;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    private StorageReference storageReference;

    private ShapeableImageView profileImage;
    private Button saveBtn;
    private EditText username;
    private LinearLayout back;

    public static final String PREFS_NAME = "MyPrefsFile";

    private Uri profileUri;

    FirebaseStorage mStorage;
    FirebaseAuth mAuth;
    FirebaseDatabase mDb;

    UserListViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        changeImage = findViewById(R.id.change_profile_btn);

        profileImage = findViewById(R.id.profile_image);
        saveBtn = findViewById(R.id.save_btn);
        username = findViewById(R.id.username);


        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        mDb.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserModel userModel = snapshot.getValue(UserModel.class);
                        Glide.with(getApplicationContext()).load(userModel.getImage()).apply(RequestOptions.centerCropTransform()).into(profileImage);
                        username.setText(snapshot.child("username").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 33);
            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }
        });

        viewModel = ViewModelProviders.of(this).get(UserListViewModel.class);
        viewModel.getUserListObserver();


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData() != null) {
                profileUri = data.getData();
                profileImage.setImageURI(profileUri);

            }

    }


    private void updateUserProfile() {
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle("File Uploader");
        pd.show();
        final StorageReference reference = mStorage.getReference().child("profile_image")
                .child(FirebaseAuth.getInstance().getUid());
        reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_LONG).show();

                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        mDb.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                .child("image").setValue(uri.toString());
                        mDb.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                .child("username").setValue(username.getText().toString());
                        Toast.makeText(getApplicationContext(), "Profile Uploaded", Toast.LENGTH_LONG).show();
                        pd.dismiss();

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent=(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                pd.setMessage("Uploaded :"+(int)percent+"%");
                if (percent==1000){
                    viewModel.postProfilePicture();
                    viewModel.putProfilePicture();
                }
            }
        });
    }








}