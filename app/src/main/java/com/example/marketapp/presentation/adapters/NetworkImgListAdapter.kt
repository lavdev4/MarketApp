package com.example.marketapp.presentation.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketapp.presentation.adapters.viewholders.NetworkImgViewHolder

class NetworkImgListAdapter(
    private val imagesUrls: List<String>
) : RecyclerView.Adapter<NetworkImgViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkImgViewHolder {
        return NetworkImgViewHolder(getImageView(parent.context))
    }

    override fun onBindViewHolder(holder: NetworkImgViewHolder, position: Int) {
        holder.imageSource = imagesUrls[position]
    }

    override fun getItemCount(): Int {
        return imagesUrls.size
    }

    private fun getImageView(context: Context): ImageView {
        return ImageView(context).apply {
            id = View.generateViewId()
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_CENTER
        }
    }
}