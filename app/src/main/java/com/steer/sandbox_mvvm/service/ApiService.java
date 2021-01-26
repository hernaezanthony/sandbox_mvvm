package com.steer.sandbox_mvvm.service;

import com.steer.sandbox_mvvm.model.Post;
import com.steer.sandbox_mvvm.model.Todo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {


    @GET("/posts")
    Call<ArrayList<Post>> getListOfPost();

    @GET("/todos")
    Call<ArrayList<Todo>> getListofTodo();
}
