package com.xpressy.firsttest.Activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;

import com.xpressy.firsttest.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsynctaskActivity extends BaseActivity {

    URL ImageUrl = null;
    InputStream inputStream = null;
    Bitmap bmImg = null;
    ImageView imageView= null;
    ProgressDialog p;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        setupActionBar("AsyncTask", true);
        Button button=findViewById(R.id.asyncTask);
        imageView=findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExample asyncTask=new AsyncTaskExample();
                asyncTask.execute("https://www.tutorialspoint.com/images/tp-logo-diamond.png");
            }
        });
        toggleButton = findViewById(R.id.tglBtn);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TOGGLE", String.valueOf(toggleButton.isChecked()));
            }
        });
        child child = new child();
        child.task();
        child.interfaceMethod();
        child.overLoad("HELLO");
        Class2 class2 = new Class2();
        class2.method();

    }

    private class AsyncTaskExample extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(AsynctaskActivity.this);
            p.setMessage("Please wait...InputStream downloading");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                ImageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
                conn.setDoInput(true);
                Log.d("CONNECT","before");
                conn.connect();
                Log.d("CONNECT","after");
                inputStream = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(inputStream, null, options);
            }
            catch (IOException  e){
                e.printStackTrace();
                Log.d("ERROR",e.toString());
            }

            return bmImg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (imageView!=null){
                p.hide();
                imageView.setImageBitmap(bitmap);
            }
            else {
                p.show();
            }
        }
    }

    public abstract static class Parent{
       abstract void task();
    }

    public interface Interface{
        void interfaceMethod();
    }

    public static class child extends Parent implements Interface{
        @Override
        void task() {
            Log.d("RUN", "task");
        }

        @Override
        public void interfaceMethod() {
            Log.d("RUN", "interfaceMethod");
        }

        void overLoad(int a){
            Log.d("OVERLOAD1", String.valueOf(a));
        }

        void overLoad(String a){
            Log.d("OVERLOAD2", a);
        }
    }

    public static class Class1{
        void method(){
            Log.d("Class1", "HELLO");
        }
    }

    public static class Class2 extends Class1{

        @Override
        void method() {
            super.method();
            Log.d("Class2", "WORLD");
        }
    }
}
