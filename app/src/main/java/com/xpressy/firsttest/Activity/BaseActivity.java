package com.xpressy.firsttest.Activity;


import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.xpressy.firsttest.R;

public class BaseActivity extends AppCompatActivity {


    public void setupActionBar(String title, boolean isBackArrow){
//        Toolbar toolbar = findViewById(R.id.toolBar);

        MaterialToolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isBackArrow);
    }

}
