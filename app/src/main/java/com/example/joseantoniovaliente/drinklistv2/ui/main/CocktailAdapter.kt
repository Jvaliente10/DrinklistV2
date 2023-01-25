package com.example.joseantoniovaliente.drinklistv2.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.databinding.ViewCocktailBinding
import com.example.joseantoniovaliente.drinklistv2.inflate
import com.example.joseantoniovaliente.drinklistv2.loadUrl
import com.example.joseantoniovaliente.drinklistv2.model.Drink

class CocktailAdapter(var drinkList : List<Drink>, val listener: (Drink) -> Unit):
    RecyclerView.Adapter<CocktailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_cocktail, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(drinkList[position])
        holder.itemView.setOnClickListener{
            listener(drinkList[position])
        }
    }

    override fun getItemCount(): Int = drinkList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ViewCocktailBinding.bind(view)
        fun bind(drink: Drink){
            binding.tvCocktailName.text = drink.strDrink
            binding.ivFlag.loadUrl(drink.strDrinkThumb)
        }
    }
}