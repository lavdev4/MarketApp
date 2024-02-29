package com.example.marketapp.presentation.adapters.diffutills

import androidx.recyclerview.widget.DiffUtil
import com.example.marketapp.domain.entities.Product

object ProductsDiffUtil : DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}