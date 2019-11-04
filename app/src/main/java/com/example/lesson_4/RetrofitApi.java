package com.example.lesson_4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApi {
    @GET("users/{user}/repos")
    Call<List<UserRepositoryModel>> loadRepository  (@Path("user") String user);
}
