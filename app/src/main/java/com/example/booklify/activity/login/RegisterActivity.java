package com.example.booklify.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.booklify.R;
import com.example.booklify.activity.HomeActivity;
import com.example.booklify.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private TextInputEditText textUsername, textEmail, textPassword, textConfirmPassword;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerButton = findViewById(R.id.registerButton);
        textUsername = findViewById(R.id.username);
        textEmail = findViewById(R.id.email);
        textPassword = findViewById(R.id.password);
        textConfirmPassword = findViewById(R.id.confirmPassword);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();
        String username = textUsername.getText().toString().trim();
        String confirmPassword = textConfirmPassword.getText().toString().trim();

            if (username.isEmpty()){
                textUsername.setError("Username is required");
                textUsername.requestFocus();
                return;
            }

            if (email.isEmpty()){
                textEmail.setError("Email is required");
                textEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                textEmail.setError("Please provide valid email!");
                textEmail.requestFocus();
                return;
            }

            if (password.isEmpty()){
            textPassword.setError("Password is required");
            textPassword.requestFocus();
            return;
            }

            if (confirmPassword.isEmpty()){
            textConfirmPassword.setError("Confirm Password is required");
            textConfirmPassword.requestFocus();
            return;
            }

            if(password.length() < 6){
            textPassword.setError("Minimum password length should be 6 characters!");
                textPassword.requestFocus();
            return;
            }

            if (!password.equals(confirmPassword)){
            textConfirmPassword.setError("Password doesn't match!");
            textConfirmPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            UserModel userModel = new UserModel(username, email);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("Users");
                            myRef.child(FirebaseAuth.getInstance().getUid())
                                    .setValue(userModel)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(),"Registration is successfully done!",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    });
                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}