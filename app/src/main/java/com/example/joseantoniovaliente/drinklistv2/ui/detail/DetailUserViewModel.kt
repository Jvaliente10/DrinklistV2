package com.example.joseantoniovaliente.drinklistv2.ui.detail

import androidx.lifecycle.*
import com.example.joseantoniovaliente.drinklistv2.model.db.CocktailDb
import com.example.joseantoniovaliente.drinklistv2.model.db.DbFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailUserViewModel(private val cocktailDb: CocktailDb) : ViewModel() {
    private val _state = MutableLiveData(UiState())
    val details: MutableLiveData<UiState> get() = _state



    fun updateCocktailName(newName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.updateCocktailName(cocktailDb, newName)
        }
    }
    fun deleteCocktail(){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.deleteCocktail(cocktailDb)
        }
    }
    init {

        val nameDrink=cocktailDb.nombre


        viewModelScope.launch(Dispatchers.Main) {
            _state.value = _state.value?.copy(loading = true)
            _state.value = _state.value?.copy(loading = false, details = DbFirestore.getCocktailByName(nameDrink))




        }
    }






    data class UiState(
        val loading: Boolean = false,
        val details: CocktailDb? = null,
        val navigateTo: CocktailDb? = null
    )
}
@Suppress("UNCHECKED_CAST")
class DetailUserViewmodelFactory(private val cocktailDb: CocktailDb): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailUserViewModel(cocktailDb) as T
    }

}