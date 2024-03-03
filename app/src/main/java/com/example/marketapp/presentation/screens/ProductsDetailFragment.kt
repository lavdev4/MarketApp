package com.example.marketapp.presentation.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.marketapp.R
import com.example.marketapp.databinding.FragmentProductDetailsBinding
import com.example.marketapp.domain.entities.ProductDetail
import com.example.marketapp.presentation.MainActivity
import com.example.marketapp.presentation.adapters.NetworkImgListAdapter
import com.example.marketapp.presentation.states.ScreenState
import com.example.marketapp.presentation.viewmodels.DetailScreenVM
import com.example.marketapp.presentation.viewmodels.MainVMFactory
import com.example.marketapp.presentation.viewmodels.ScreenStateVM
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class ProductsDetailFragment : Fragment() {
    @Inject lateinit var viewModelFactory: MainVMFactory
    private val screenStateVM by activityViewModels<ScreenStateVM> { viewModelFactory }
    private val fragmentViewModel by viewModels<DetailScreenVM> { viewModelFactory }
    private val args: ProductsDetailFragmentArgs by navArgs()
    private var tabLayoutMediator: TabLayoutMediator? = null
    private var _hostActivity: MainActivity? = null
    private val hostActivity: MainActivity
        get() = _hostActivity ?: throw RuntimeException("Host activity is detached")
    private var _binding: FragmentProductDetailsBinding? = null
    private val binding: FragmentProductDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentProductDetailsBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (requireActivity() !is MainActivity) {
            throw RuntimeException(
                "Host activity of ${this::class} can be only ${MainActivity::class}"
            )
        }
        _hostActivity = requireActivity() as MainActivity
        hostActivity.mainActivitySubcomponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeScreenData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        hostActivity.binding.toolbar.tabLayout.removeAllTabs()
        tabLayoutMediator?.detach()
        _hostActivity = null
        super.onDetach()
    }

    private fun observeScreenData() {
        fragmentViewModel.productData.observe(viewLifecycleOwner) { requestResult ->
            if (requestResult == null) {
                initializeData(args.productId)
            } else {
                requestResult
                    .onSuccess { data ->
                        screenStateVM.setScreenState(ScreenState.Presenting)
                        fillUi(data)
                    }
                    .onFailure { error ->
                        screenStateVM.setScreenState(ScreenState.Error(error))
                    }
            }
        }
    }

    private fun initializeData(productId: Int) {
        screenStateVM.setScreenState(ScreenState.Loading)
        fragmentViewModel.initialize(productId)
    }

    private fun fillUi(data: ProductDetail) {
        val imagesList =  data.images.map { it.image }
        setupImagesList(imagesList)
        setParametersSection(
            title = data.name,
            description = data.details,
            price = data.price.toString(),
            discountPrice = data.price.toString(),
            priceFormatter = { price: String -> "$ $price,-" }
        )
        val reviews = data.reviews.joinToString("\n\n") {
            getString(R.string.comment_pattern, it.firstName, it.lastName, it.message)
        }
        setInformationSection(reviews)
    }

    private fun setupImagesList(imagesUrls: List<String>) {
        binding.imagesPager.adapter = NetworkImgListAdapter(imagesUrls)
        tabLayoutMediator = createTabLayoutMediator()
        tabLayoutMediator?.attach()
    }

    private fun createTabLayoutMediator(): TabLayoutMediator {
        val imagesPager = binding.imagesPager
        val tabLayout = hostActivity.binding.toolbar.tabLayout
        return TabLayoutMediator(tabLayout, imagesPager) { _, _ -> }
    }

    private fun setParametersSection(
        title: String,
        description: String,
        price: String,
        discountPrice: String,
        priceFormatter: (String) -> String
    ) {
        with(binding.parametersSection) {
            this.title.text = title
            this.description.text = description
            this.price.text = priceFormatter(price)
            this.discountPrice.text = priceFormatter(discountPrice)
        }
    }

    private fun setInformationSection(description: String) {
        binding.informationSection.description.text = description
    }
}