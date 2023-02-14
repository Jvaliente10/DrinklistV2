package com.example.joseantoniovaliente.drinklistv2.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.joseantoniovaliente.drinklistv2.model.db.CocktailDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailUserViewModel(cocktailDb: CocktailDb) : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val details: MutableLiveData<UiState> get() = _state
    init {

        val nameDrink=cocktailDb.nombre


        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)




        }
    }



    data class UiState(
        val loading: Boolean = false,
        val details: List<CocktailDb>? = null,
        val navigateTo: CocktailDb? = null
    )
}
@Suppress("UNCHECKED_CAST")
class DetailUserViewmodelFactory(private val cocktailDb: CocktailDb): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailUserViewModel(cocktailDb) as T
    }

}