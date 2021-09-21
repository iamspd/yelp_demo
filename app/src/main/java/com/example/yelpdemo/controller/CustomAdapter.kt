package com.example.yelpdemo.controller

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.yelpdemo.R
import com.example.yelpdemo.YelpRestaurants
import com.example.yelpdemo.view.RestaurantDetailsActivity

class CustomAdapter(
    private var context: Context?,
    var listItem: List<YelpRestaurants>
) : BaseAdapter() {

    override fun getCount(): Int {
        if (listItem.size <= 20) {
            return listItem.size
        } else {
            return 20
        }
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View? {
        var layoutInflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var listView = view
        if (listView == null) {
            listView = layoutInflater.inflate(R.layout.grid_list_item, viewGroup, false)
        }

        var listItemImage = listView?.findViewById<ImageView>(R.id.ivPhoto)
        if (listItemImage != null) {
            Glide.with(context!!).load(listItem[position].imageUrl).into(listItemImage)
        }


        var listItemName = listView?.findViewById<TextView>(R.id.tvName)
        if (listItemName != null) {
            listItemName.text = listItem[position].name
        }

        var listItemAddress = listView?.findViewById<TextView>(R.id.tvAddress)
        if (listItemAddress != null) {
            listItemAddress.text = listItem[position].location.address
        }

        var listItemCard = listView?.findViewById<CardView>(R.id.card_list_item)
        listItemCard?.setOnClickListener {
            var intent = Intent(context, RestaurantDetailsActivity::class.java)
            intent.putExtra("image", listItem[position].imageUrl)
            intent.putExtra("name", listItem[position].name)
            intent.putExtra("address", listItem[position].location.address)
            intent.putExtra("id", listItem[position].id)

            Log.i("Id", listItem[position].id)

            context!!.startActivity(intent)
        }

        return listView
    }
}