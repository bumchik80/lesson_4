package com.example.lesson_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.os.Bundle;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RepositoryAdapter adapter;
    private List<String> Repositories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestRetrofit("bumchik80");
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RepositoryAdapter(Repositories, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void requestRetrofit(final String user) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OpenRepository.getSingleton().getAPI().loadRepository(user)
                        .enqueue(new Callback<List<UserRepositoryModel>>() {
                            @Override
                            public void onResponse(Call<List<UserRepositoryModel>> call, Response<List<UserRepositoryModel>> response) {
                                if (response.isSuccessful()) {
                                    UserRepositoryModel userRepositoryModel = null;
                                    for (int i = 0; i < response.body().size(); i++) {
                                        userRepositoryModel = response.body().get(i);
                                        Repositories.add(userRepositoryModel.name);
                                        System.out.println(userRepositoryModel.name);
                                    }
                                } else {
                                    System.out.println("onResponse error: " + response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<List<UserRepositoryModel>> call, Throwable t) {
                                System.out.println("Failure: " + t.getMessage());
                            }

                        });

            }
        }).start();
    }
}
