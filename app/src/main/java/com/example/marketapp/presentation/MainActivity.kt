package com.example.marketapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import com.example.marketapp.R
import com.example.marketapp.databinding.ActivityMainBinding
import com.example.marketapp.di.MainActivitySubcomponent
import com.example.marketapp.presentation.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var mainActivitySubcomponent: MainActivitySubcomponent
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivitySubcomponent =
            (application as MarketApplication).applicationComponent
            .activitySubcomponent()
            .build()
        mainActivitySubcomponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setupNavigationGraph()
    }

//    private fun setupNavigationGraph() {
//        navController = binding.navHostFragment.getFragment<NavHostFragment>().navController
//        val navGraph = navController.navInflater.inflate(R.navigation.main_graph)
//        navGraph.setStartDestination(cafesGraphId)
//        navController.graph = navGraph
//        graphViewModel.setGraphState(GraphState.CafesGraph)
//    }
}