package com.example.myretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import lombok.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtText = findViewById(R.id.txtText);

        NetworkService
                .getInstance()
                .getRetrofitApi()
                .getPostWithID(1)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Post post = response.body();

                        txtText.append(Integer.toString(post.getId()));
                        txtText.append(post.getTitle());
                        txtText.append(post.getBody());
                        txtText.append(Integer.toString(post.getUserId()));
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        txtText.append("Error occured while getting request");
                        t.printStackTrace();
                    }
                });
    }
}