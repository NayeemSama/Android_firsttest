package com.xpressy.firsttest.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xpressy.firsttest.Adapter.VolleyAdapter;
import com.xpressy.firsttest.Model.Jackson.Datum;
import com.xpressy.firsttest.Model.Jackson.Jackson;
import com.xpressy.firsttest.Model.VolleyModel;
import com.xpressy.firsttest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolleyActivity extends BaseActivity{

    String URL = "https://api.instantwebtools.net/v1/passenger?page=0&size=10";

    VolleyAdapter adapter;
    ArrayList<VolleyModel> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton fab;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        recyclerView = findViewById(R.id.lvVOLLEY);
        button = findViewById(R.id.btnVOLLEY);
        fab = findViewById(R.id.fabVOLLEY);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postRequest();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
                //getDataUsingJackson();
            }
        });

    }


    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = response;
                        try {
                            JSONArray data = object.getJSONArray("data");
                            for (int i=0; i<data.length(); i++){
                                VolleyModel model = new VolleyModel();
                                JSONObject unit = data.getJSONObject(i);
                                String name = unit.getString("name");
                                model.setName(name);

                                JSONArray airline = unit.getJSONArray("airline");
                                JSONObject airlineObject = airline.getJSONObject(0);
                                String airlineName = airlineObject.getString("name");
                                model.setAirline(airlineName);
                                String country = airlineObject.getString("country");
                                model.setCountry(country);
                                arrayList.add(model);
                                Log.d("DATA", unit.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(VolleyActivity.this));
                        adapter = new VolleyAdapter(VolleyActivity.this, arrayList);
                        recyclerView.setAdapter(adapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", error.toString());

                    }
                }
        );

        queue.add(objectRequest);
    }

    private void postRequest() {

        String postURL = "https://api.instantwebtools.net/v1/passenger";
//        String postURL = "https://api.themoviedb.org/3/authentication/session/new?api_key=59f1582165daf34054cc783100d84133";


        //https://api.instantwebtools.net/v1/passenger/:id TO CHECK!!!!!!

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, postURL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
//                params.put("request_token", "073504cc3083616711410d7687531654ab82a027");
                params.put("name", "ROHAN");
                params.put("trips", "111");
                params.put("airline", "11");

                return params;
            }
        };
        queue.add(postRequest);

    }

    private void getDataUsingJackson(){
        final ObjectMapper mapper = new ObjectMapper();
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Jackson jackson = mapper.readValue(new URL(URL), Jackson.class);
                    // read from url CREATE model in jsonshema2pojo.com!!!!
                    List<Datum> list = jackson.getData();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            for (int i=0; i<list.size();i++){
                                Datum datum = list.get(i);
                                Log.d("ID", datum.getId());
                                Log.d("NAME", datum.getName());
                            }
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
