package com.example.joseantoniovaliente.drinklistv2.model.server


import com.example.joseantoniovaliente.drinklistv2.model.DetailsList
import com.example.joseantoniovaliente.drinklistv2.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query


interface CocktailDbService {

    @GET("filter.php?a=Alcoholic")
    suspend fun drinkList(): DrinkList

    @GET("lookup.php")
    suspend fun getCocktailById(@Query("i") searchQuery: String?): DetailsList
}
