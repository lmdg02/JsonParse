package com.example.ldd.duongldph04549_lab3.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();

}
