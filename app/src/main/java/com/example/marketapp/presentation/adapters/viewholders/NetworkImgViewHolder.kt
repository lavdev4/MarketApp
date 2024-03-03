package com.example.marketapp.presentation.adapters.viewholders

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.marketapp.R
import com.squareup.picasso.Picasso

class NetworkImgViewHolder(private val imageView: ImageView) : ViewHolder(imageView) {
    var imageSource: String? = null
        set(value) {
            value?.let { loadImage(it) }
            field = value
        }

    private fun loadImage(source: String) {
        Picasso.get()
            .load(source)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_error_placeholder)
            .into(imageView)
    }
}