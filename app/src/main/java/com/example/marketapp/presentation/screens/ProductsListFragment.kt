package com.example.marketapp.presentation.screens

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketapp.databinding.FragmentProductsListBinding
import com.example.marketapp.domain.entities.Product
import com.example.marketapp.presentation.MainActivity
import com.example.marketapp.presentation.adapters.ProductsListAdapter
import com.example.marketapp.presentation.adapters.diffutills.ProductsDiffUtil
import com.example.marketapp.presentation.adapters.viewholders.ProductsViewHolder
import com.example.marketapp.presentation.states.ScreenState
import com.example.marketapp.presentation.viewmodels.MainVMFactory
import com.example.marketapp.presentation.viewmodels.ProductsVM
import com.example.marketapp.presentation.viewmodels.ScreenStateVM
import com.google.android.material.divider.MaterialDividerItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductsListFragment : Fragment() {
    @Inject lateinit var viewModelFactory: MainVMFactory
    private val screenStateVM by activityViewModels<ScreenStateVM> { viewModelFactory }
    private val fragmentViewModel by viewModels<ProductsVM> { viewModelFactory }
    private var _binding: FragmentProductsListBinding? = null
    private val binding: FragmentProductsListBinding
        get() = _binding ?: throw RuntimeException("FragmentProductsListBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainActivitySubcomponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productsAdapter = setProductsAdapter()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { observeProductsLoadState(productsAdapter) }
                launch { observeProducts(productsAdapter) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setProductsAdapter(): ProductsListAdapter {
        val dividerDecoration = MaterialDividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        ).apply {
            dividerInsetEnd = convertDpToPixel(20f, requireContext()).toInt()
        }
        val usdPriceFormatter = { price: String -> "$ $price,-" }
        val itemClickListener = { itemId: Int -> navigateToDetails(itemId) }
        val adapter = ProductsListAdapter(ProductsDiffUtil, usdPriceFormatter, itemClickListener)
        with(binding.productsList) {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(dividerDecoration)
            this@with.adapter = adapter
        }
        return adapter
    }

    private suspend fun observeProducts(
        productsAdapter: PagingDataAdapter<Product, ProductsViewHolder>
    ) {
        fragmentViewModel.productsFlow.collectLatest { data ->
            productsAdapter.submitData(data)
        }
    }

    private suspend fun observeProductsLoadState(
        productsAdapter: PagingDataAdapter<Product, ProductsViewHolder>
    ) {
        productsAdapter.loadStateFlow.collectLatest { state ->
            Log.d("adapter", state.toString())
            reactToProductsState(state)
        }
    }

    private fun reactToProductsState(state: CombinedLoadStates) {
        when(state.refresh) {
            LoadState.Loading -> screenStateVM.setScreenState(ScreenState.Loading)
            is LoadState.NotLoading -> screenStateVM.setScreenState(ScreenState.Presenting)
            is LoadState.Error -> {
                val error = (state.refresh as LoadState.Error).error
                screenStateVM.setScreenState(ScreenState.NetworkError(error))
            }
        }
    }

    private fun navigateToDetails(productId: Int) {

    }

    private fun convertDpToPixel(dp: Float, context: Context): Float {
        val displayPixelDensity = context.resources.displayMetrics.densityDpi
        return dp * (displayPixelDensity.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}