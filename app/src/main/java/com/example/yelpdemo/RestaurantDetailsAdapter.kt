package com.example.yelpdemo

import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RestaurantDetailsAdapter(var context: Context, var reviews: List<YelpReviews>) :
    RecyclerView.Adapter<RestaurantDetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = reviews[position]
        holder.bind(review)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val view = itemView
        fun bind(reviews: YelpReviews) {
            var listItemImage = view.findViewById<ImageView>(R.id.ivUserProfilePhoto)
            Glide.with(context).load(reviews.userDetails.profilePhotoUrl).into(listItemImage)

            Log.i("Url", reviews.userDetails.profilePhotoUrl)

            var listItemName = view.findViewById<TextView>(R.id.tvUserName)
            listItemName.text = reviews.userDetails.name

            Log.i("Name", reviews.userDetails.name)

            var listItemTime = view.findViewById<TextView>(R.id.tvReview_time)
            listItemTime.text = reviews.dateTime

            Log.i("Name", reviews.userDetails.name)
            var listItemReview = view.findViewById<TextView>(R.id.tvReview_text)
            listItemReview.text = reviews.text

        }

    }
}