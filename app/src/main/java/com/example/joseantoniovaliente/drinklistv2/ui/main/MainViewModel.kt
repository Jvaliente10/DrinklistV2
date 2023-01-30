package com.example.joseantoniovaliente.drinklistv2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joseantoniovaliente.drinklistv2.model.Drink
import com.example.joseantoniovaliente.drinklistv2.model.server.RemoteConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel():ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            val getDrinks = RemoteConnection.service.drinkList()
            val drinkList= getDrinks.drinks
            _state.value = _state.value?.copy(loading = false, drinks = drinkList)
        }
    }
    data class UiState(
        val loading: Boolean = false,
        val drinks: List<Drink>? = null,
        val navigateTo: Drink? = null
    )
}