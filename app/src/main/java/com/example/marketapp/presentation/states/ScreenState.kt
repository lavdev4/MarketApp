package com.example.marketapp.presentation.states

import androidx.navigation.NavDirections

sealed class ScreenState {
    data object Initial : ScreenState()
    data object Loading : ScreenState()
    data object Presenting : ScreenState()
    data class Navigating(val direction: NavDirections) : ScreenState()
    data class NetworkError(val error: Throwable) : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
}