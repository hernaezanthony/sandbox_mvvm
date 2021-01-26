package com.steer.sandbox_mvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.steer.sandbox_mvvm.model.Todo;
import com.steer.sandbox_mvvm.service.ApiHelper;
import com.steer.sandbox_mvvm.service.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoRepository {
    private ApiService todoApiService = ApiHelper.getApiService();

    public MutableLiveData<ArrayList<Todo>> todoMutableLiveData = new MutableLiveData<>();

    public TodoRepository(){

    }

    public void getListOfTodo(){
        todoApiService.getListofTodo().enqueue(new Callback<ArrayList<Todo>>() {
            @Override
            public void onResponse(Call<ArrayList<Todo>> call, Response<ArrayList<Todo>> response) {
                todoMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Todo>> call, Throwable t) {
                todoMutableLiveData.postValue(null);
            }
        });
    }

}
