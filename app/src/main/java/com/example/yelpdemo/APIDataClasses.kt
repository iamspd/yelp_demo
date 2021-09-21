package com.example.yelpdemo

import com.google.gson.annotations.SerializedName

data class APISearchResults (
    @SerializedName("businesses") val restaurants: List<YelpRestaurants>
)

data class YelpRestaurants(
    val name: String,
    val id: String,
    @SerializedName("image_url") val imageUrl: String,
    val location: YelpLocation
)

data class YelpLocation(
    @SerializedName("address1") val address: String
)

data class APIReviewResults (
    val reviews: List<YelpReviews>
)

data class YelpReviews(
    val rating: Int,
    @SerializedName("user") val userDetails: YelpUserDetails,
    val text: String,
    @SerializedName("time_created") val dateTime: String
)

data class YelpUserDetails(
    @SerializedName("image_url") val profilePhotoUrl: String,
    val name: String
)