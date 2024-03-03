package com.example.marketapp.presentation.adapters.viewholders

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.marketapp.R
import com.squareup.picasso.Picasso

class NetworkImgViewHolder(private val imageView: ImageView) : ViewHolder(imageView) {
    var imageSource: String
        set(value) { loadImage(value) }
        get() = imageSource

    private fun loadImage(source: String) {
        Picasso.get()
            .load(source)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_error_placeholder)
            .resize(800, 0)
            .centerCrop()
            .into(imageView)
    }
}