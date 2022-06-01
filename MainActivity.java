package com.example.target;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);

        apiInterface.getPosts().enqueue(new Callback<List<PostDataPojo>>() {
            @Override
            public void onResponse(Call<List<PostDataPojo>> call, Response<List<PostDataPojo>> response) {

                if(response.body().size()>0){
                    List<PostDataPojo> list = response.body();
                    PostAdapter adapter = new PostAdapter(MainActivity.this,list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, list.size()+"Not Empty", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostDataPojo>> call, Throwable t) {

            }
        });
    }
}