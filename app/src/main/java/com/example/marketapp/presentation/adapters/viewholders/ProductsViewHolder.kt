package com.example.marketapp.presentation.adapters.viewholders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketapp.R
import com.example.marketapp.databinding.ItemProductsListBinding
import com.example.marketapp.domain.entities.Product
import com.squareup.picasso.Picasso

class ProductsViewHolder(
    private val binding: ItemProductsListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Product?, priceFormatter: (String) -> String) {
        item?.let { data ->
            with(binding) {
                title.text = data.name
                description.text = data.details
                price.text = priceFormatter.invoke(data.price.toString())
                discountPrice.text = priceFormatter.invoke(data.price.toString())
                loadImage(data.mainImage, image)
            }
        }
    }

    private fun loadImage(source: String, target: ImageView) {
        Picasso.get()
            .load(source)
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_error_placeholder)
            .resize(300, 0)
            .centerCrop()
            .into(target)
    }
}