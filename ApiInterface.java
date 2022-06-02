package com.example.target;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("posts")
    Call<List<PostDataPojo>> getPosts();


    @GET("photos")
    Call<List<PhotoPojo>> getPhotos();
    
}
