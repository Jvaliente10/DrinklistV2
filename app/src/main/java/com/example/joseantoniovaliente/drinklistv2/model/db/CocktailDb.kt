package com.example.joseantoniovaliente.drinklistv2.model.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CocktailDb(
    var nombre: String="",
    var ingredientes: String="",
    var instrucciones: String="",
    var imagen: String=""

): Parcelable{

}

