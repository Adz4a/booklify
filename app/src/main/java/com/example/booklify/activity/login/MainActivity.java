package com.example.booklify.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.booklify.R;
import com.example.booklify.activity.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button registerButton, loginButton;
    private TextInputEditText textEmail, textPassword;
    private TextView resetPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        registerButton = findViewById(R.id.register);

        loginButton = findViewById(R.id.login);
        textEmail = findViewById(R.id.email);
        textPassword = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        resetPassword = findViewById(R.id.resetPassword);

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.resetPassword:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.login:
                onResume();
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Tag", "HomeActivity: onResume()");
        String email = textEmail.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

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
        if(password.isEmpty()){
            textPassword.setError("Password is required");
            textPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            textPassword.setError("Number of characters should be more than 6!");
            textPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(), "Failed to login! Please check your credentials!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }



}