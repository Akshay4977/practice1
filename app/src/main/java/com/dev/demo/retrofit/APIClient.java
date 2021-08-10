package com.dev.demo.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String base_url = "https://www.breakingbadapi.com/api/";

    private static Retrofit retrofit = null;
    private static Retrofit retrofitForNews = null;


    public static Retrofit getRetrofitClient() {

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}