package com.xpressy.firsttest.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xpressy.firsttest.Adapter.OkHttpAdapter;
import com.xpressy.firsttest.Model.OkHttpModel;
import com.xpressy.firsttest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends BaseActivity{

    RecyclerView recyclerView;
    OkHttpAdapter adapter;
    FloatingActionButton fab;
    Button button;
    TextInputEditText tvname,tvjob;

    public String postUrl= "https://reqres.in/api/users/";
    public String postBody="{\n" + "    \"name\": \"NAYEEM\",\n" + "    \"job\": \"SOFTWARE\"\n" + "}";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        tvname = findViewById(R.id.tvusernameOKHTTP);
        tvjob = findViewById(R.id.tvjobOKHTTP);
        recyclerView = findViewById(R.id.lvOKHTTP);
        fab = findViewById(R.id.fabOKHTTP);
        button = findViewById(R.id.btnOKHTTP);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                postData(postUrl, postBody);
                postBASIC();
            }
        });
    }

    void postBASIC() {

        OkHttpPOST okHttpPOST = new OkHttpPOST();
        okHttpPOST.execute("https://api.instantwebtools.net/v1/passenger");

    }

//        OkHttpClient client = new OkHttpClient();
//        RequestBody formBody = new FormBody.Builder()
//                .add("name", "Your ROHAN")
//                .add("trips", "555")
//                .add("airline", "55")
//                .build();
//        Request request = new Request.Builder()
//                .url("https://api.instantwebtools.net/v1/passenger")
//                .post(formBody)
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            Log.d("RESPONSE", response.toString());
//            // Do something with the response.
//        } catch (IOException e) {
//            Log.d("RESPONSE", e.toString());
//            e.printStackTrace();
//        }

    void postData(String postUrl,String postBody) {


        OkHttpModel model = new OkHttpModel();

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, postBody);

        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {

            String name,job;
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                try {

                    JSONObject object = new JSONObject(response.body().string());
                    Log.d("RES", object.toString());
                    name = object.getString("name");
                    job = object.getString("job");

                } catch (JSONException e) {
                    Log.d("ERROR",response.body().string());
                    e.printStackTrace();
                }
                OkHttpActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvname.setText(name);
                        tvjob.setText(job);
                    }
                });
            }
        });




    }

    private void getData() {
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute("https://reqres.in/api/users");
    }

    public class OkHttpHandler extends AsyncTask<String, Void, String> {

        ArrayList<OkHttpModel> arrayList = new ArrayList<>();

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray data = object.getJSONArray("data");

                for (int i=0;i<data.length();i++){
                    OkHttpModel model = new OkHttpModel();
                    JSONObject unit = data.getJSONObject(i);
                    String first = unit.getString("first_name");
                    model.setFirstName(first);
                    String last = unit.getString("last_name");
                    model.setLastName(last);

                    arrayList.add(model);
                }

                Log.d("RESULT", object.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(OkHttpActivity.this));
            adapter = new OkHttpAdapter(OkHttpActivity.this, arrayList);
            recyclerView.setAdapter(adapter);

        }
    }


    public class OkHttpPOST extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            RequestBody formBody = new FormBody.Builder()
                    .add("name", "Your ROHAN")
                    .add("trips", "555")
                    .add("airline", "55")
                    .build();
            Request request = new Request.Builder()
                    .url(params[0])
                    .post(formBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("RESPONSE", s);
            JSONObject object = null;
            try {
                object = new JSONObject(s);
                Log.d("RES", object.toString());
                tvname.setText(object.getString("name"));
                tvjob.setText(object.getString("job"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

//                try {
//                    JSONObject object = new JSONObject(s);
//                    JSONArray data = object.getJSONArray("data");
//
//                    for (int i=0;i<data.length();i++){
//                        OkHttpModel model = new OkHttpModel();
//                        JSONObject unit = data.getJSONObject(i);
//                        String first = unit.getString("first_name");
//                        model.setFirstName(first);
//                        String last = unit.getString("last_name");
//                        model.setLastName(last);
//
//                        arrayList.add(model);
//                    }
//
//                    Log.d("RESULT", object.toString());
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }

//                recyclerView.setLayoutManager(new LinearLayoutManager(OkHttpActivity.this));
//                adapter = new OkHttpAdapter(OkHttpActivity.this, arrayList);
//                recyclerView.setAdapter(adapter);

        }
    }

}
