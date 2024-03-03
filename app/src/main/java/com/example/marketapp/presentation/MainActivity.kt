package com.example.marketapp.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.example.marketapp.R
import com.example.marketapp.databinding.ActivityMainBinding
import com.example.marketapp.di.MainActivitySubcomponent
import com.example.marketapp.presentation.screens.ErrorDialogDirections
import com.example.marketapp.presentation.states.ScreenState
import com.example.marketapp.presentation.viewmodels.MainVMFactory
import com.example.marketapp.presentation.viewmodels.ScreenStateVM
import retrofit2.HttpException
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var mainActivitySubcomponent: MainActivitySubcomponent
    @Inject lateinit var viewModelFactory: MainVMFactory
    private val screenStateVM by viewModels<ScreenStateVM> { viewModelFactory }
    lateinit var binding: ActivityMainBinding
    private var navController: NavController? = null
    private var destinationChangedListener: NavController.OnDestinationChangedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivitySubcomponent =
            (application as MarketApplication).applicationComponent
            .activitySubcomponent()
            .build()
        mainActivitySubcomponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initNavController()
        initDestinationListener()
        addDestinationListener()
        observeScreenStates()
    }

    override fun onDestroy() {
        removeDestinationListener()
        super.onDestroy()
    }

    /** Must be used only after [onCreate]. */
    private fun initNavController() {
        navController = findNavController(binding.navHostFragment.id)
    }

    private fun initDestinationListener() {
        destinationChangedListener =
            NavController.OnDestinationChangedListener { _, destination, _ ->
            reactOnDestinationChange(destination)
        }
    }

    private fun addDestinationListener() {
        navController?.let { controller ->
            destinationChangedListener?.let { listener ->
                controller.addOnDestinationChangedListener(listener)
            }
        }
    }

    private fun removeDestinationListener() {
        navController?.let { controller ->
            destinationChangedListener?.let { listener ->
                controller.removeOnDestinationChangedListener(listener)
            }
        }
    }

    private fun reactOnDestinationChange(destination: NavDestination) {
        when (destination.id) {
            R.id.productsListFragment -> configureProductsListToolbar()
            R.id.productsDetailFragment -> configureProductDetailToolbar()
        }
    }

    private fun configureProductsListToolbar() {
        with(binding.toolbar) {
            val leftDrawable = ResourcesCompat.getDrawable(resources, R.drawable.icon_user, theme)
            leftImage.apply {
                setImageDrawable(leftDrawable)
                setOnClickListener(null)
            }
            imageLogo.visibility = View.VISIBLE
            tabLayout.visibility = View.GONE
        }
    }

    private fun configureProductDetailToolbar() {
        with(binding.toolbar) {
            val leftDrawable = ResourcesCompat.getDrawable(resources, R.drawable.icon_back, theme)
            leftImage.apply {
                setImageDrawable(leftDrawable)
                navController?.let { controller ->
                    setOnClickListener { controller.navigateUp() }
                }
            }
            imageLogo.visibility = View.GONE
            tabLayout.visibility = View.VISIBLE
        }
    }

    /** **Warning:** [ScreenState] may provide a navigation direction that will lead to navigation.
     * Since [navController] is initialized only at [onStart], this method can be called
     * no earlier than [onStart] of the activity’s life cycle. */
    private fun observeScreenStates() {
        screenStateVM.screenState.observe(this) { event ->
            event.getContentIfNotHandled()?.let { state ->
                when(state) {
                    ScreenState.Initial -> {}
                    ScreenState.Loading -> showProgress(true)
                    ScreenState.Presenting -> showProgress(false)
                    is ScreenState.Navigating -> navController?.navigate(state.direction)
                    is ScreenState.NetworkError -> {
                        showProgress(false)
                        showNetworkErrorDialog(state.error)
                    }
                    is ScreenState.Error -> {
                        showProgress(false)
                        showErrorDialog(state.error)
                    }
                }
            }
        }
    }

    private fun showProgress(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showNetworkErrorDialog(error: Throwable) {
        val title = getString(R.string.dialog_network_error_title)
        val message = when(error) {
            is HttpException -> getString(
                R.string.dialog_http_error_message,
                error.code().toString(),
                error.message()
            )
            else -> getString(R.string.dialog_network_error_message, error.localizedMessage)
        }
        val direction = ErrorDialogDirections.actionGlobalErrorDialog(title, message)
        navController?.navigate(direction)
    }

    private fun showErrorDialog(error: Throwable) {
        val title = getString(R.string.dialog_error_title)
        val message = getString(R.string.dialog_error_message, error.localizedMessage)
        val direction = ErrorDialogDirections.actionGlobalErrorDialog(title, message)
        navController?.navigate(direction)
    }
}