package com.xpressy.firsttest.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.xpressy.firsttest.Interface.Api;
import com.xpressy.firsttest.Model.GetModel;
import com.xpressy.firsttest.Model.PostModel;
import com.xpressy.firsttest.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitActivity extends AppCompatActivity {

    ListView listView;
    Button post;
    TextInputEditText name,job;
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        listView = findViewById(R.id.lvGet);
        post = findViewById(R.id.btnPOST);
        name = findViewById(R.id.usernamePOST);
        job = findViewById(R.id.jobsPOST);
        fab = findViewById(R.id.fabRETRO);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();
            }
        });
    }

    private void postData() {

        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(Api.POST_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api retroapi = retrofit.create(Api.class);

        PostModel postModel = new PostModel();
        postModel.setName(name.getText().toString());
        postModel.setJob(job.getText().toString());

        Call<PostModel> call = retroapi.createPost(postModel);

        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                PostModel postModel1 = response.body();

                Toast.makeText(RetroFitActivity.this, "Name :- "+postModel1.getName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(RetroFitActivity.this, " Job :- "+postModel1.getJob(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(RetroFitActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getData() {

        Call<List<GetModel>> call = RetroFitClient.getInstance().getMyApi().getsuperHeroes();
        call.enqueue(new Callback<List<GetModel>>() {
            @Override
            public void onResponse(Call<List<GetModel>> call, Response<List<GetModel>> response) {
                List<GetModel> list = response.body();
                String[] names = new String[list.size()];
                for (int i=0; i<list.size(); i++){
                    names[i] = list.get(i).getName();
                }
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names));

            }

            @Override
            public void onFailure(Call<List<GetModel>> call, Throwable t) {
                Toast.makeText(RetroFitActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataUsingLoganSquare(){

    }
}
