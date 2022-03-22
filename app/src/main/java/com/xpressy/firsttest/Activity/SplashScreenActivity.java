package com.xpressy.firsttest.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.xpressy.firsttest.R;

public class SplashScreenActivity extends BaseActivity {

    private ImageView imageView;
    private AnimatedVectorDrawable emptyHeart;
    private AnimatedVectorDrawable fillHeart;
    private boolean full = false;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        imageView = findViewById(R.id.heartSPLASH);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        emptyHeart = (AnimatedVectorDrawable) getDrawable(R.drawable.draw_empty_heart);
        fillHeart = (AnimatedVectorDrawable) getDrawable(R.drawable.draw_fill_heart);


        final Handler handler = new Handler();
        final int[] count = {0};

        final Runnable runnable = new Runnable() {
            public void run() {
                // need to do tasks on the UI thread
                animate();
                if (count[0]++ < 5) {
                    handler.postDelayed(this, 600);
                }
                else {
                    Intent i = new Intent(SplashScreenActivity.this, DashboardActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        handler.post(runnable);

    }

    private void animate() {



        AnimatedVectorDrawable drawable
                = full
                ? emptyHeart
                : fillHeart;
        imageView.setImageDrawable(drawable);
        drawable.start();
        full = !full;


    }
}
