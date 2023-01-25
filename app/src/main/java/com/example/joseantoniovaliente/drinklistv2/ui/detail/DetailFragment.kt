package com.example.joseantoniovaliente.drinklistv2.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentDetailBinding
import com.example.joseantoniovaliente.drinklistv2.loadUrl
import com.example.joseantoniovaliente.drinklistv2.model.Drink


class DetailFragment() : Fragment(R.layout.fragment_detail) {
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(
            arguments?.getParcelable<Drink>(
                DetailFragment.EXTRA_DRINK
            )!!
        )
    }


    companion object {
        const val EXTRA_DRINK = "DetailFragment:DrinkDetail"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        viewModel.details.observe(viewLifecycleOwner) { details ->
            val drinkDetail = details.details?.get(0)
            if (drinkDetail != null) {
                binding.imagen.loadUrl(drinkDetail.strDrinkThumb)
                binding.nombre.text ="Name: "+ drinkDetail.strDrink
                binding.category.text ="Drink category: " + drinkDetail.strCategory
                binding.glass.text="Glass: " + drinkDetail.strGlass
                binding.instructions.text ="Instructions: " + drinkDetail.strInstructions
                (requireActivity() as AppCompatActivity).supportActionBar?.title = drinkDetail.idDrink

            }


        }
    }
}