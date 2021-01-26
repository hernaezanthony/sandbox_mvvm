package com.steer.sandbox_mvvm.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.steer.sandbox_mvvm.model.Post;
import com.steer.sandbox_mvvm.service.ApiHelper;
import com.steer.sandbox_mvvm.service.ApiService;
import com.steer.sandbox_mvvm.service.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRepository {

    private ApiService apiService = ApiHelper.getApiService();

    public MutableLiveData<ArrayList<Post>> postsMutableLiveData = new MutableLiveData<>();

    public PostRepository() {
    }

    public void getListOfPost(){

        apiService.getListOfPost().enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                postsMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                postsMutableLiveData.postValue(null);
            }
        });
    }

}
