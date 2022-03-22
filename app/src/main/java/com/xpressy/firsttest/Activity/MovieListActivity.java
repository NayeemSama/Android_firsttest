package com.xpressy.firsttest.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xpressy.firsttest.Adapter.MovieAdapter;
import com.xpressy.firsttest.Adapter.MovieGridAdapter;
import com.xpressy.firsttest.Model.MovieModel;
import com.xpressy.firsttest.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MovieListActivity extends BaseActivity{

    MovieAdapter adapter;
    MovieGridAdapter gridAdapter;
    ArrayList<MovieModel> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton fab;
    String URL = "https://api.themoviedb.org/3/list/564?api_key=59f1582165daf34054cc783100d84133&language=en-US";
    SwipeRefreshLayout swipeRefreshLayout;

    int page = 1, limit = 2;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);
//        swipeRefreshLayout = findViewById(R.id.swipingRefresh);

        recyclerView = findViewById(R.id.lvMOVIE);
        fab = findViewById(R.id.fabMOVIE);
//        gridView = findViewById(R.id.simpleGridView);

//        recyclerView = findViewById(R.id.lvMOVIEPAGE);
//        fab = findViewById(R.id.fabMOVIEPAGE);

        loadingPB = findViewById(R.id.progressBar);
        nestedSV = findViewById(R.id.nestedScrollView);

//        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
//                    // in this method we are incrementing page number,
//                    // making progress bar visible and calling get data method.
//
//                    page++;
//                    loadingPB.setVisibility(View.VISIBLE);
//
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            getMoviePagination(page, limit);
//                        }
//                    }, 2000);
//
//                }
//            }
//        });
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        Toast.makeText(MovieListActivity.this, "Refreshed!!", Toast.LENGTH_SHORT).show();
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 2000);
//            }
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMovie();
//                getGridMovie();
//                getMoviePagination(page, limit);
            }
        });




    }

// req token    073504cc3083616711410d7687531654ab82a027

    // 36fe167fa210a1efef9747425e8cd2ebf5b62b91

    private void getMovie() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject data = response;
                        try {
                            JSONArray item = data.getJSONArray("items");
                            for (int i=0; i<item.length(); i++){
                                MovieModel movieModel = new MovieModel();
                                JSONObject object = item.getJSONObject(i);
                                String title = object.getString("original_title");
                                movieModel.setTitle(title);
                                String overview = object.getString("overview");
                                movieModel.setOverview(overview);
                                String date = object.getString("release_date");
                                movieModel.setDate(date);
                                String rating = object.getString("vote_average");
                                movieModel.setRating(rating);
                                String image = object.getString("poster_path");
                                movieModel.setImage(image);
                                int id = object.getInt("id");
                                movieModel.setMovieId(id);

                                arrayList.add(movieModel);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(MovieListActivity.this));
                        adapter = new MovieAdapter(MovieListActivity.this, arrayList, new MovieAdapter.Onclick() {
                            @Override
                            public void watchClicked(int movieId) {
                                postMovie(movieId);
                            }
                        });
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

    private void postMovie(int id) {

        String postURL = "https://api.themoviedb.org/3/account/{account_id}/watchlist?api_key=59f1582165daf34054cc783100d84133&session_id=36fe167fa210a1efef9747425e8cd2ebf5b62b91";


        //https://api.instantwebtools.net/v1/passenger/:id TO CHECK!!!!!!

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest postRequest = new StringRequest(Request.Method.POST, postURL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        Toast.makeText(MovieListActivity.this, "Added to Watchlist", Toast.LENGTH_SHORT).show();
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

                params.put("media_type", "movie");
                params.put("media_id", String.valueOf(id));
                params.put("watchlist", "true");

                return params;
            }
        };
        queue.add(postRequest);

    }

    private void getGridMovie(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject data = response;
                        try {
                            JSONArray item = data.getJSONArray("items");
                            for (int i=0; i<item.length(); i++){
                                MovieModel movieModel = new MovieModel();
                                JSONObject object = item.getJSONObject(i);
                                String title = object.getString("original_title");
                                movieModel.setTitle(title);
                                String overview = object.getString("overview");
                                movieModel.setOverview(overview);
                                String date = object.getString("release_date");
                                movieModel.setDate(date);
                                String rating = object.getString("vote_average");
                                movieModel.setRating(rating);
                                String image = object.getString("poster_path");
                                movieModel.setImage(image);
                                int id = object.getInt("id");
                                movieModel.setMovieId(id);

                                arrayList.add(movieModel);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                        gridAdapter = new MovieGridAdapter(MovieListActivity.this, arrayList);
                        recyclerView.setAdapter(gridAdapter);

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

    private void getMoviePagination(int page, int limit){
        loadingPB.setVisibility(View.GONE);

        String pageURL = "https://api.themoviedb.org/3/account/nayeem12345/watchlist/movies?api_key=59f1582165daf34054cc783100d84133&language=en-US&session_id=36fe167fa210a1efef9747425e8cd2ebf5b62b91&sort_by=created_at.asc&page=" + page;
        if (page > limit) {
            // checking if the page number is greater than limit.
            // displaying toast message in this case when page>limit.
            Toast.makeText(this, "That's all the data..", Toast.LENGTH_SHORT).show();

            // hiding our progress bar.
            loadingPB.setVisibility(View.GONE);
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, pageURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject data = response;
                        Log.d("TAG", response.toString());
                        try {
                            JSONArray item = data.getJSONArray("results");
                            for (int i=0; i<item.length(); i++){
                                MovieModel movieModel = new MovieModel();
                                JSONObject object = item.getJSONObject(i);
                                String title = object.getString("original_title");
                                movieModel.setTitle(title);
                                String overview = object.getString("overview");
                                movieModel.setOverview(overview);
                                String date = object.getString("release_date");
                                movieModel.setDate(date);
                                String rating = object.getString("vote_average");
                                movieModel.setRating(rating);
                                String image = object.getString("poster_path");
                                movieModel.setImage(image);
                                int id = object.getInt("id");
                                movieModel.setMovieId(id);

                                arrayList.add(movieModel);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        recyclerView.setLayoutManager(new LinearLayoutManager(MovieListActivity.this));
                        adapter = new MovieAdapter(MovieListActivity.this, arrayList, new MovieAdapter.Onclick() {
                            @Override
                            public void watchClicked(int movieId) {
                                postMovie(movieId);
                            }
                        });
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

}
