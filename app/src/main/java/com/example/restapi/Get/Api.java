package com.example.restapi.Get;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] userId, //more conditions
//            @Query("userId") Integer userId1,  match userId or userId1
            @Query("_sort") String sort,
            @Query("_order") String order);

    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String,String> parameters);
    //get with parameter for var
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);
    //get from url
    @GET
    Call<List<Comment>> getComments(@Url String url);

    //post
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    //put
    //if you put a null parameter it will be null
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id,@Body Post post);
    //if you patch a null parameter it will be not changed
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id,@Body Post post);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);


}
//Query các loại yêu cầu same where hoặc các tác vụ như sort
//Path giúp thay thế var trong câu lệnh get giúp thay đổi chúng