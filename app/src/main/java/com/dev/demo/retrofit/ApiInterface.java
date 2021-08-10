package com.dev.demo.retrofit;

import com.dev.demo.models.Characters;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("characters")
    Call<List<Characters>> getCharacter();
}
