package com.example.mydota.Data.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://api.opendota.com")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static DotaAPI dotaAPI = retrofit.create(DotaAPI.class);

    public static DotaAPI getDotaApi() {
        return dotaAPI;
    }
}
