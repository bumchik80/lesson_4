package com.example.lesson_4;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OpenRepository {

    private static OpenRepository singleton = null;
    private RetrofitApi API;

    private OpenRepository() {
        API = createAdapter();
    }

    public static OpenRepository getSingleton() {
        if(singleton == null) {
            singleton = new OpenRepository();
        }

        return singleton;
    }

    public RetrofitApi getAPI() {
        return API;
    }

    private RetrofitApi createAdapter() {
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return adapter.create(RetrofitApi.class);
    }
}
