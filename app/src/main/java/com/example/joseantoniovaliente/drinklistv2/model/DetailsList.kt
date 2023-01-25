package com.example.joseantoniovaliente.drinklistv2.model


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailsList(
    @SerializedName("drinks")
    val drinks: List<DrinkDetails>
) : Parcelable