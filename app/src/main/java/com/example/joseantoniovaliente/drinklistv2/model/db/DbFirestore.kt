package com.example.joseantoniovaliente.drinklistv2.model.db

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

object DbFirestore {
    const val COLLECTION_USERS = "usuarios"
    const val COLLECTION_COCKTAILS = "Cocteles"
    private val currentUser = FirebaseAuth.getInstance().currentUser

    suspend fun getAll(): List<CocktailDb> {

        val snapshot = currentUser?.let {
            FirebaseFirestore.getInstance()
                .collection(COLLECTION_USERS)
                .document(it.uid)
                .collection(COLLECTION_COCKTAILS)
                .get().await()
        }
        val cocktails = mutableListOf<CocktailDb>()
        if (snapshot != null) {
            for (documentSnapshot in snapshot) {
                val cocktail = documentSnapshot.toObject(CocktailDb::class.java)
                cocktails.add(cocktail)
            }
        }
        return cocktails
    }
    suspend fun createCocktail(cocktail: CocktailDb) {
        currentUser?.let {
            FirebaseFirestore.getInstance()
                .collection(COLLECTION_USERS)
                .document(currentUser.email.toString())
                .collection(COLLECTION_COCKTAILS)
                .whereEqualTo("nombre", cocktail.nombre)
                .get()
                .addOnSuccessListener { result ->
                    if (result.isEmpty) {
                        val documentRef = FirebaseFirestore.getInstance()
                            .collection(COLLECTION_USERS)
                            .document(currentUser.email.toString())
                            .collection(COLLECTION_COCKTAILS)
                            .document(cocktail.nombre)
                        documentRef.set(cocktail)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // coctel agregado con éxito
                                } else {
                                    // error al agregar el coctel
                                }
                            }
                    } else {
                        // ya existe un coctel con el mismo nombre
                    }
                }
                .addOnFailureListener { exception ->
                    // error al buscar el coctel
                }
        }
    }



    suspend fun updateCocktailName(currentCocktail: CocktailDb, newName: String){
        FirebaseFirestore.getInstance().collection(COLLECTION_COCKTAILS)
            .whereEqualTo("nombre", currentCocktail.nombre)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val id = task.result.first().id
                    FirebaseFirestore.getInstance().collection(COLLECTION_COCKTAILS)
                        .document(id)
                        .update("nombre", newName)
                        .addOnCompleteListener{
                            if (it.isSuccessful){
                                Log.d(COLLECTION_COCKTAILS, "Nombre del cóctel actualizado correctamente")
                            }
                        }
                        .addOnFailureListener{
                            Log.e(COLLECTION_COCKTAILS, it.toString())
                        }
                }
            }
    }

    suspend fun deleteCocktail(cocktail: CocktailDb){
        currentUser?.let {
            FirebaseFirestore.getInstance()
                .collection(COLLECTION_USERS)
                .document(it.uid)
                .collection(COLLECTION_COCKTAILS)
                .whereEqualTo("nombre", cocktail.nombre)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val id = it.result.first().id
                        FirebaseFirestore.getInstance()
                            .collection(COLLECTION_USERS)
                            .document(currentUser.uid)
                            .collection(COLLECTION_COCKTAILS)
                            .document(id)
                            .delete()
                            .addOnCompleteListener {
                                if (it.isSuccessful){
                                    Log.d(COLLECTION_COCKTAILS,id)
                                }
                            }
                            .addOnFailureListener{
                                Log.e(COLLECTION_COCKTAILS,it.toString())
                            }
                    }
                }
        }

    }

    fun getFlow(): Flow<List<CocktailDb>> {
        return FirebaseFirestore.getInstance()
            .collection(COLLECTION_COCKTAILS)
            .orderBy("nombre", Query.Direction.DESCENDING)
            .snapshots().map { snapshot ->
                snapshot.toObjects(CocktailDb::class.java)
            }
    }


}