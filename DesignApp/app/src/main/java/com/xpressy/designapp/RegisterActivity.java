package com.xpressy.designapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,password;
    Button create;
    private FirebaseAuth mAuth;
    TextView login;
// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.nameREGISTER);
        email = findViewById(R.id.emailREGISTER);
        password = findViewById(R.id.passwordREGISTER);
        create = findViewById(R.id.createREGISTER);
        login = findViewById(R.id.loginREGISTER);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    mAuth
                            .createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(RegisterActivity.this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(i);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });


    }

    private boolean isValid() {
        boolean flag  = true;
        name.setError(null);
        if (TextUtils.isEmpty(name.getText())){
            name.setError("Enter name");
            flag =  false;
        }

        email.setError(null);
        if (TextUtils.isEmpty(email.getText())){
            email.setError("Enter email");
            flag =  false;
        }

        password.setError(null);
        if (TextUtils.isEmpty(password.getText())){
            password.setError("Enter password");
            flag =  false;
        }

        return flag;
    }
}
