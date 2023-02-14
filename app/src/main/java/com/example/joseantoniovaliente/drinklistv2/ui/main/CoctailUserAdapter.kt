package com.example.joseantoniovaliente.drinklistv2.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.databinding.ViewCocktailBinding
import com.example.joseantoniovaliente.drinklistv2.inflate
import com.example.joseantoniovaliente.drinklistv2.loadUrl
import com.example.joseantoniovaliente.drinklistv2.model.db.CocktailDb

class CoctailUserAdapter(var userDrinkList : List<CocktailDb>, val listener: (CocktailDb) -> Unit):
    RecyclerView.Adapter<CoctailUserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_cocktail, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userDrinkList[position])
        holder.itemView.setOnClickListener{
            listener(userDrinkList[position])
        }
    }

    override fun getItemCount(): Int = userDrinkList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ViewCocktailBinding.bind(view)
        fun bind(cocktailDb: CocktailDb){
            binding.tvCocktailName.text = cocktailDb.nombre
            binding.ivFlag.loadUrl(cocktailDb.imagen)
        }
    }
}