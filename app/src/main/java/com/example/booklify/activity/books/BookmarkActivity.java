package com.example.booklify.activity.books;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklify.R;
import com.example.booklify.adapter.BookmarkAdapter;
import com.example.booklify.model.BookmarkModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class BookmarkActivity extends AppCompatActivity {

    LinearLayout back;
    RecyclerView recyclerView;
    List<BookmarkModel> bookmarkModel;
    BookmarkAdapter bookmarkAdapter;

    private FirebaseFirestore mDb;
    private FirebaseAuth mAuth;
    private String bookId;
    private ProgressBar progressBar;
    private TextView isEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        mDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);
        isEmpty = findViewById(R.id.isEmpty);

        EditText editText = findViewById(R.id.filter);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());
            }
        });

        recyclerView = findViewById(R.id.bookmarkRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        bookmarkModel = new ArrayList<>();
        bookmarkAdapter = new BookmarkAdapter(bookmarkModel,this );
        recyclerView.setAdapter(bookmarkAdapter);



        if(mAuth.getCurrentUser() != null) {

            mDb.collection("CurrentUser").document(mAuth.getCurrentUser().getUid())
                    .collection("bookmark").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().getDocuments().isEmpty()) {
                                    isEmpty.setVisibility(View.VISIBLE);
                                }
                                else {
                                    isEmpty.setVisibility(View.INVISIBLE);
                                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                        bookId = documentSnapshot.getId();
                                        BookmarkModel bookmarkModels = documentSnapshot.toObject(BookmarkModel.class);
                                        bookmarkModels.setDocumentId(bookId);
                                        bookmarkModel.add(bookmarkModels);
                                        bookmarkAdapter.notifyDataSetChanged();



                                    }
                                }
                            }
                        }
                    });
        }


    }

    private void filter(String text) {
        ArrayList<BookmarkModel> filteredList = new ArrayList<>();

        for (BookmarkModel item : bookmarkModel) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        bookmarkAdapter.filterList(filteredList);
    }
}