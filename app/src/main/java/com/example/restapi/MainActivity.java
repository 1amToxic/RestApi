package com.example.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restapi.Get.Api;
import com.example.restapi.Get.Comment;
import com.example.restapi.Get.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView textViewResult;
    Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResult =  findViewById(R.id.text_result);
        //add null object if you patch a null parameter
        Gson gson = new GsonBuilder().serializeNulls().create();
        //OkHttp for logging what you request and response
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        api = retrofit.create(Api.class);
//        getComments();
//        getPosts();
//        createPost();
        updatePost();
//        deletePost();
    }
    private void getPosts(){
        Map<String,String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_order", "asc");
        parameters.put("_sort", "id");
        Call<List<Post>> callPost = api.getPosts(parameters);
//        Call<List<Post>> callPost = api.getPosts(new Integer[]{2,4,6},"id","desc");
        callPost.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post>posts = response.body();
                for(Post post : posts){
                    String text = "UserId: "+post.getUserId()+"\n"
                            +"Id: "+post.getId()+"\n"
                            +"Title: "+post.getTitle()+"\n"
                            +"Body: "+post.getBody()+"\n\n";
                    textViewResult.append(text);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getComments() {
        Call<List<Comment>> call = api.getComments("posts/1/comments");
//        Call<List<Comment>> call = api.getComments(3);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code "+response.code());
                    return;
                }
                List<Comment> comments = response.body();
                for(Comment comment: comments){
                    String text = "postId "+comment.getPostId()+"\n"
                            +"id "+comment.getId()+"\n"
                            +"name "+comment.getName()+"\n"
                            +"email "+comment.getEmail()+"\n"
                            +"body "+comment.getText()+"\n\n";
                    textViewResult.append(text);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createPost(){
        Post post = new Post(23,"New Title", "New Text");
        Call<Post> callPost = api.createPost(post);
        callPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code "+response.code());
                    return;
                }
                Post postRespone = response.body();
                String content = "";
                content += "Code: "+response.code()+"\n"
                        +"ID: "+postRespone.getId()+"\n"
                        +"User Id: "+postRespone.getUserId()+"\n"
                        +"Title: "+postRespone.getTitle()+"\n"
                        +"Text: "+postRespone.getBody()+"\n\n";
                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
    private void updatePost(){
        Post post = new Post(12,null, "New Text");
//        Call<Post> callPost = api.putPost(5, post);
        Call<Post> callPost = api.patchPost(5, post);
        callPost.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code "+response.code());
                    return;
                }
                Post postRespone = response.body();
                String content = "";
                content += "Code: "+response.code()+"\n"
                        +"ID: "+postRespone.getId()+"\n"
                        +"User Id: "+postRespone.getUserId()+"\n"
                        +"Title: "+postRespone.getTitle()+"\n"
                        +"Text: "+postRespone.getBody()+"\n\n";
                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
    private void deletePost(){
        Call<Void> call = api.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResult.setText("Code: "+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
