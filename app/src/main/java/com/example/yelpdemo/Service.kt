package com.example.yelpdemo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

public interface Service{

    @GET("businesses/search")
    fun searchRestaurants(
        @Header("Authorization") authKey: String,
        @Query("term") searchTerm: String,
        @Query("location") location: String
    ) : Call<APISearchResults>

    @GET("businesses/{id}/reviews")
    fun getRestaurantReviews(
        @Header("Authorization") authKey: String,
        @Path("id") id: String
    ) : Call<APIReviewResults>
}