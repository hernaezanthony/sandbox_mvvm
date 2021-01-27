package com.steer.sandbox_mvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.steer.sandbox_mvvm.model.Image;
import com.steer.sandbox_mvvm.service.ApiHelper;
import com.steer.sandbox_mvvm.service.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository {
    private ApiService imageApiService = ApiHelper.getApiService();

    public MutableLiveData<ArrayList<Image>> mutableImageDataSet = new MutableLiveData<>();

    public ImageRepository(){

    }

    public void getListOfImage(){
        imageApiService.getListofImage().enqueue(new Callback<ArrayList<Image>>() {
            @Override
            public void onResponse(Call<ArrayList<Image>> call, Response<ArrayList<Image>> response) {
                mutableImageDataSet.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Image>> call, Throwable t) {
                mutableImageDataSet.postValue(null);
            }
        });
    }
}
