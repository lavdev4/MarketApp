package com.example.marketapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marketapp.presentation.states.Event
import com.example.marketapp.presentation.states.ScreenState
import javax.inject.Inject

class ScreenStateVM @Inject constructor() : ViewModel() {
    private val _screenState =
        MutableLiveData<Event<ScreenState>>(Event(ScreenState.Initial))
    val screenState: LiveData<Event<ScreenState>>
        get() = _screenState

    fun setScreenState(state: ScreenState) {
        if (_screenState.value?.peekContent() == state) return
        _screenState.value = Event(state)
    }
}