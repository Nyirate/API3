package com.example.api1.network;

import com.example.api1.models.YelpBusinessesSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpApi {
    @GET("businesses/search")
    Call<YelpBusinessesSearchResponse> getMovies(
            @Query("location") String location,
            @Query("term") String term
    );
}
