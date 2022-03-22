package com.xpressy.secondtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecondTest extends AppCompatActivity {

    public static String MESSAGE = "Second Test Module";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_test);
    }
}