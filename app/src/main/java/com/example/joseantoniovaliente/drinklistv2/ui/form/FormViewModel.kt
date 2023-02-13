package com.example.joseantoniovaliente.drinklistv2.ui.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.joseantoniovaliente.drinklistv2.model.db.CocktailDb
import com.example.joseantoniovaliente.drinklistv2.model.db.DbFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FormViewModel : ViewModel() {

    fun createCoctel(cocktailDb: CocktailDb){
        viewModelScope.launch(Dispatchers.IO) {
            DbFirestore.createCocktail(cocktailDb)
        }
    }


}
