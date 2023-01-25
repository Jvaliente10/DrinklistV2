package com.example.joseantoniovaliente.drinklistv2.ui.detail

import androidx.lifecycle.*
import com.example.joseantoniovaliente.drinklistv2.model.Drink
import com.example.joseantoniovaliente.drinklistv2.model.DrinkDetails
import com.example.joseantoniovaliente.drinklistv2.model.server.RemoteConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(drink: Drink) : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val details: MutableLiveData<UiState> get() = _state

    init {

        val idDrink=drink.idDrink


        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)

                val drinkDetail = RemoteConnection.service.getCocktailById(idDrink)
                val detailList = drinkDetail.drinks
                _state.value = _state.value?.copy(loading = false, details = detailList)


        }
    }

    data class UiState(
        val loading: Boolean = false,
        val details: List<DrinkDetails>? = null,
        val navigateTo: Drink? = null
    )

}
@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val drink: Drink): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(drink) as T
    }

}