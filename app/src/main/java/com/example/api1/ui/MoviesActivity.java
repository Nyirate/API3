package com.example.api1.ui;


import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api1.R;
import com.example.api1.adapters.MoviesListAdapter;
import com.example.api1.network.YelpApi;
import com.example.api1.network.YelpClient;
import com.example.api1.models.Business;
import com.example.api1.models.Category;
import com.example.api1.models.YelpBusinessesSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {

    private TextView mTel;
    private TextView mTel2;
    private TextView mTel5;
    private String city;
    private TextView mErrorTextView;
    private RecyclerView mRecycler;
    private ProgressBar mProgressBar;
    private EditText editLocation;
    private  MoviesListAdapter mAdapter;
    public List<Business> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mErrorTextView = (TextView) findViewById(R.id.error);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);



        Intent intent = getIntent();

        String location = intent.getStringExtra("location");
        Toast.makeText(this, "" + location, Toast.LENGTH_SHORT).show();

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getMovies(location, "movie");
        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    movies = response.body().getBusinesses();
                    mAdapter = new MoviesListAdapter(MoviesActivity.this, movies);
                    mRecycler.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(MoviesActivity.this);
                    mRecycler.setLayoutManager(layoutManager);
                    mRecycler.setHasFixedSize(true);

                    showMovies();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showMovies() {
        mRecycler.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
