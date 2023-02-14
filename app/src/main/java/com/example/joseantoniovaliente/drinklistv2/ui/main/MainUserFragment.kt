package com.example.joseantoniovaliente.drinklistv2.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentMainBinding
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentMainUserBinding
import com.example.joseantoniovaliente.drinklistv2.model.Drink
import com.example.joseantoniovaliente.drinklistv2.model.db.CocktailDb
import com.example.joseantoniovaliente.drinklistv2.ui.detail.DetailFragment
import com.example.joseantoniovaliente.drinklistv2.ui.detail.DetailUserFragment
import kotlinx.coroutines.launch

class MainUserFragment : Fragment(R.layout.fragment_main_user) {
    private lateinit var viewModel: MainUserViewModel

    private lateinit var binding: FragmentMainUserBinding
    private val adapter = CoctailUserAdapter(emptyList()){drink -> navigateTo(drink)  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainUserBinding.bind(view).apply {

            recycler.adapter = adapter
            (requireActivity() as AppCompatActivity).supportActionBar?.title = "DrinkList"
        }
        viewModel = ViewModelProvider(this).get(MainUserViewModel::class.java)

        viewModel.state.observe(viewLifecycleOwner){state ->
            binding.progress.visibility =  if (state.loading) View.VISIBLE else View.GONE
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    state.coctails?.collect() {
                        adapter.userDrinkList = it
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }

    }

    private fun navigateTo(cocktailDb: CocktailDb) {
        findNavController().navigate(
            R.id.action_userMainFragment_to_userDetailFragment,
            bundleOf(DetailUserFragment.EXTRA_COCKTAIL to cocktailDb)
        )

    }
}