package com.example.marketapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.marketapp.databinding.ItemProductsListBinding
import com.example.marketapp.domain.entities.Product
import com.example.marketapp.presentation.adapters.viewholders.ProductsViewHolder

class ProductsListAdapter(
    dataComparator: DiffUtil.ItemCallback<Product>,
    private val priceFormatter: (input: String) -> String,
    private val onItemClick: (itemId: Int) -> Unit
) : PagingDataAdapter<Product, ProductsViewHolder>(dataComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ItemProductsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewHolder = ProductsViewHolder(binding)
        binding.root.setOnClickListener {
            getItem(viewHolder.bindingAdapterPosition)?.let { onItemClick(it.id) }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, priceFormatter)
    }
}
