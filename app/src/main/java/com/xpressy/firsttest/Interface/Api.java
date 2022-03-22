package com.xpressy.firsttest.Interface;

import com.xpressy.firsttest.Model.GetModel;
import com.xpressy.firsttest.Model.PostModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<GetModel>> getsuperHeroes();

    String POST_URL = "https://reqres.in/api/";
    @POST("users")
    Call<PostModel> createPost(@Body PostModel postModel);

    @GET("maps/api/directions/json")
    Single<Result> get(@Query("mode") String mode,
                       @Query("transit_routing_preference") String preference,
                       @Query("origin") String origin,
                       @Query("destination") String destination,
                       @Query("key") String key);
}
