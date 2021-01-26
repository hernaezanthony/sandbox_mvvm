package com.steer.sandbox_mvvm.service;

public class ApiHelper {

    public static ApiService getApiService(){

        return RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }
}
