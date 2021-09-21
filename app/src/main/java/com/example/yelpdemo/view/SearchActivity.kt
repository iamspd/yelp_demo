package com.example.yelpdemo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.yelpdemo.R

class SearchActivity : AppCompatActivity() {

    private lateinit var searchRestaurants: EditText
    private lateinit var searchLocale: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchRestaurants = findViewById(R.id.etSearch_restaurants)
        searchLocale = findViewById(R.id.etSearch_locale)
    }

    fun onSearchClick(view: View) {
        var restaurantName = searchRestaurants.text.toString()
        var restaurantLocale = searchLocale.text.toString()

        var intent = Intent(this@SearchActivity, MainActivity::class.java)
        intent.putExtra("name", restaurantName)
        intent.putExtra("locale", restaurantLocale)

        startActivity(intent)

    }
}