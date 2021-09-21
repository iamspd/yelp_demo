package com.example.yelpdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// constants
private const val TAG = "RestaurantActivity"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_KEY =
    "FhtucBGllg9klHY_M4tfwhRLN_5cuDOQVHQMcf4Og188O5lWfqpP7lv34FLQzfZ_DL_I-Dn9EK6HeiCbjlvnRKAnqUmPmIuK0nN1Dnnm2edsbOM2FDqYxzvpw7FAYXYx"

class RestaurantDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_details)

        var coverImage = findViewById<ImageView>(R.id.ivCoverPhoto)
        var restaurantName = findViewById<TextView>(R.id.tvRestaurantName)
        var restaurantAddress = findViewById<TextView>(R.id.tvRestaurantAddress)

        var bundle = intent.extras
        Glide.with(this).load(bundle!!.getString("image")).into(coverImage)
        restaurantName.text = bundle.getString("name")
        restaurantAddress.text = bundle.getString("address")
        var id = bundle.getString("id")

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitService = retrofit.create(Service::class.java)
        retrofitService.getRestaurantReviews("Bearer $API_KEY", "$id")
            .enqueue(object: Callback<APIReviewResults> {
                override fun onResponse(
                    call: Call<APIReviewResults>,
                    response: Response<APIReviewResults>
                ) {
                    Log.i(TAG, "onResponse $response")
                }

                override fun onFailure(call: Call<APIReviewResults>, t: Throwable) {
                    Log.i(TAG, "onFailure $t")
                }

            })
    }
}