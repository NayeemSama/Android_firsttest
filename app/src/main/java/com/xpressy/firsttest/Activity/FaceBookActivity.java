package com.xpressy.firsttest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.xpressy.firsttest.R;

import java.util.Arrays;

public class FaceBookActivity extends BaseActivity{

    CallbackManager callbackManager;
    LoginButton loginButton;
    private FirebaseAuth firebaseAuth;
    private static final String EMAIL = "email";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        firebaseAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.btnfbLOGIN);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        callbackManager = CallbackManager.Factory.create();
        Log.d("MANAGER", callbackManager.toString());
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                faceBookLogin();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void faceBookLogin() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.w("RESULT", loginResult.toString());
                handleFacebookAccessToken(loginResult.getAccessToken());
//                Toast.makeText(FaceBookActivity.this, loginResult.toString(), Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(FaceBookActivity.this, AboutActivity.class);
//                startActivity(i);
                //  Toast.makeText(FaceBookActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                Log.d("SUCCESS", "SUCCESS");
            }

            @Override
            public void onCancel() {
//                Toast.makeText(FaceBookActivity.this, "Login Cancel", Toast.LENGTH_SHORT).show();
                Log.w("CANCEL", "SUCCESS");
            }

            @Override
            public void onError(FacebookException error) {
//                Toast.makeText(FaceBookActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.w("ERROR", "SUCCESS");
            }

        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TOKEN!!!", "handleFacebookAccessToken:" + token);
//eb41bc2
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        Log.d("TAGGG", credential.toString());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SUCCESS!!", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FAILURE!!!", "signInWithCredential:failure", task.getException());
                            Toast.makeText(FaceBookActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }
}
