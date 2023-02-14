package com.example.joseantoniovaliente.drinklistv2.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentDetailBinding
import com.example.joseantoniovaliente.drinklistv2.model.db.CocktailDb

class DetailUserFragment : Fragment() {


    private val viewModel: DetailUserViewModel by viewModels {
        DetailUserViewmodelFactory(
            arguments?.getParcelable<CocktailDb>(
                DetailUserFragment.EXTRA_COCKTAIL
            )!!
        )
    }

    companion object {
        const val EXTRA_COCKTAIL = "DetailUserFragment:CoctelUserDetail"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)


    }
}