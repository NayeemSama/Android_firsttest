package com.xpressy.firsttest.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.xpressy.firsttest.Database.Tbl_User;
import com.xpressy.firsttest.R;

public class RegisterActivity extends BaseActivity{

    TextInputLayout user,pass;
    TextInputEditText username, password;
    Button button;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupActionBar("Register", false);


        username = findViewById(R.id.usernameREGSTR);
        user = findViewById(R.id.usrLayout);
        password = findViewById(R.id.passREGSTER);
        pass = findViewById(R.id.passLayout);
        button = findViewById(R.id.btnREGSTR);
        floatingActionButton = findViewById(R.id.fab);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().clearFocus();
                if (isValid()) {
                    Toast.makeText(RegisterActivity.this, username.getText().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegisterActivity.this, String.valueOf(password.getText()), Toast.LENGTH_SHORT).show();
                }
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(RegisterActivity.this, username.getText().toString(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(RegisterActivity.this, String.valueOf(password.getText()), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isValid() {
        boolean isValid = true;

        user.setError(null);
        if (TextUtils.isEmpty(username.getText().toString())){
            isValid = false;
            user.setErrorEnabled(true);
            user.setError("Enter name first");
        }
        else if (!username.getText().toString().matches("[A-Z]{1}[a-z]{1,}")){
            isValid = false;
            user.setErrorEnabled(true);
            user.setError("Enter first letter capital");
//            username.setError("UserName first letter capital!!");
        }

        pass.setError(null);
        if (TextUtils.isEmpty(password.getText().toString())){
            isValid = false;
            pass.setErrorEnabled(true);
            pass.setError("Enter Password First!!");
        }
        else if (Integer.parseInt(password.getText().toString()) < 6){
            isValid = false;
            pass.setErrorEnabled(true);
            pass.setError("Enter 6 digit!!");
        }

        return isValid;
    }
}
