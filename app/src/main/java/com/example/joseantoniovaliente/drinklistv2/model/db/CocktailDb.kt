package com.example.joseantoniovaliente.drinklistv2.model.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CocktailDb(
    val nombre: String="",
    val ingredientes: String="",
    val instrucciones: String="",
    val imagen: String=""

): Parcelable{

}

