package com.example.yelpdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

// constants
private const val TAG = "MainActivity"
private const val BASE_URL = "https://api.yelp.com/v3/"
private const val API_KEY =
    "FhtucBGllg9klHY_M4tfwhRLN_5cuDOQVHQMcf4Og188O5lWfqpP7lv34FLQzfZ_DL_I-Dn9EK6HeiCbjlvnRKAnqUmPmIuK0nN1Dnnm2edsbOM2FDqYxzvpw7FAYXYx"


class MainActivity : AppCompatActivity() {

    var viewSoretdAscending = true
    val restaurants = mutableListOf<YelpRestaurants>()
    val listItem: List<YelpRestaurants> = ArrayList()
    var adapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = CustomAdapter(this, restaurants)

        var gridView = findViewById<GridView>(R.id.gridView)
        gridView.adapter = adapter

        var bundle = intent.extras
        val restaurantName = bundle?.getString("name")
        val restaurantLocale = bundle?.getString("locale")

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        val retrofitService = retrofit.create(Service::class.java)
        if (restaurantName != null && restaurantLocale != null) {
            retrofitService.searchRestaurants("Bearer $API_KEY", restaurantName, restaurantLocale)
                .enqueue(object : Callback<APISearchResults> {
                    override fun onResponse(
                        call: Call<APISearchResults>,
                        response: Response<APISearchResults>
                    ) {
                        val body = response.body()
                        if (body == null) {
                            Log.w(TAG, "Did not receive valid response from Yelp API... exiting")
                            return
                        }
                        restaurants.addAll(body.restaurants)
                        adapter!!.notifyDataSetChanged()
                        Log.i(TAG, "onResponse $response")
                    }

                    override fun onFailure(call: Call<APISearchResults>, t: Throwable) {
                        Log.i(TAG, "onFailure $t")
                    }
                })
        }
    }

    fun sortData(view: View) {

        if (!viewSoretdAscending) {
            restaurants.sortBy { it.name }
            adapter?.notifyDataSetChanged()
            viewSoretdAscending = true
        } else {
            restaurants.sortByDescending { it.name }
            adapter?.notifyDataSetChanged()
            viewSoretdAscending = false
        }
    }
}