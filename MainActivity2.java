package com.example.target;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.MailTo;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recycler_view2);

        ApiInterface apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);

        apiInterface.getPhotos().enqueue(new Callback<List<PhotoPojo>>() {
            @Override
            public void onResponse(Call<List<PhotoPojo>> call, Response<List<PhotoPojo>> response) {
                if(response.body().size() > 0){
                    List<PhotoPojo> list = response.body();
                    PhotoAdapter adapter = new PhotoAdapter(MainActivity2.this,list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(MainActivity2.this, "Fetched", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "Unable", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PhotoPojo>> call, Throwable t) {

            }
        });
    }
}