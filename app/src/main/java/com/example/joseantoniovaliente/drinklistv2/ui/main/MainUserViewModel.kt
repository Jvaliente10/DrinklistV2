package com.example.joseantoniovaliente.drinklistv2.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.joseantoniovaliente.drinklistv2.model.Drink
import com.example.joseantoniovaliente.drinklistv2.model.db.CocktailDb
import com.example.joseantoniovaliente.drinklistv2.model.db.DbFirestore
import com.example.joseantoniovaliente.drinklistv2.model.server.RemoteConnection
import com.example.joseantoniovaliente.drinklistv2.ui.detail.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainUserViewModel : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get() = _state

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(coctails = DbFirestore.getFlow())
        }

    }
    data class UiState(
        val loading: Boolean = false,
        val coctails: Flow<List<CocktailDb>>? = null,
        val navigateTo: CocktailDb? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainUserViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainUserViewModel() as T
    }

}

