package com.example.booklify.activity.password;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.booklify.R;
import com.example.booklify.activity.login.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ChangePasswordActivity extends AppCompatActivity {


    Button enterEmail;
    TextInputEditText textEmail;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mAuth = FirebaseAuth.getInstance();

        enterEmail = findViewById(R.id.changePassword);
        textEmail = findViewById(R.id.email);

//      getIdBooks();

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.changePassword:
                resetPassword();
                break;
        }

    }

    private void resetPassword() {
        String email = textEmail.getText().toString().trim();

        if(email.isEmpty()){
            textEmail.setError("Email is required!");
            textEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            textEmail.setError("Please enter valid email!");
            textEmail.requestFocus();
            return;
        }


        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Check your email to reset your password", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else{
                    Toast.makeText(getApplicationContext(), "Try again! Something went wrong!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }




//    private void getIdBooks() {
//
//        Call<BookModel> call = bookApi.getIdBooks(2);
//
//        call.enqueue(new Callback<BookModel>() {
//            @Override
//            public void onResponse(Call<BookModel> call, Response<BookModel> response) {
//
//                if (!response.isSuccessful()) {
//                    textView.setText("Code: " + response.code());
//                    return;
//                }
//
//                BookModel book = response.body();
//
//                String content = "";
//                content += "Title: " + book.getTitle() + "\n";
//                content += "Category: " + book.getCategory() + "\n";
//                content += "Author: " + book.getAuthor() + "\n";
//                content += "Content: " + book.getContent() + "\n\n";
//
//                textView.append(content);
//
//            }
//
//            @Override
//            public void onFailure(Call<BookModel> call, Throwable t) {
//                textView.setText(t.getMessage());
//            }
//        });
//    }











}
