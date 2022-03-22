package com.xpressy.firsttest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.xpressy.firsttest.Database.Tbl_User;
import com.xpressy.firsttest.R;

public class LoginActivity extends BaseActivity{

    EditText user,pass;
    Button login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.userLOGIN);
        pass = findViewById(R.id.passLOGIN);
        login = findViewById(R.id.btnLOGIN);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()){
                    boolean r = new Tbl_User(LoginActivity.this).validUser(user.getText().toString(), pass.getText().toString());
                    if (r){
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(user.getText().toString())){
            isValid = false;
            user.setError("Enter Name First!!");
        }
        else if (!user.getText().toString().matches("[A-Z]{1}[a-z]{1,}")){
            isValid = false;
            user.setError("Username first letter capital!!");
        }

        if (TextUtils.isEmpty(pass.getText().toString())){
            isValid = false;
            pass.setError("Enter Password First!!");
        }
        else if (pass.getText().toString().length() < 6){
            isValid = false;
            pass.setError("Enter 6 digit number!!");
        }

        return isValid;

    }
}
