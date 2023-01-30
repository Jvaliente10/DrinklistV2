package com.example.joseantoniovaliente.drinklistv2.ui.main


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.joseantoniovaliente.drinklist.ui.main.MainViewModel
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentMainBinding
import com.example.joseantoniovaliente.drinklistv2.model.Drink
import com.example.joseantoniovaliente.drinklistv2.ui.detail.DetailFragment




class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: FragmentMainBinding
    private val adapter = CocktailAdapter(emptyList()){drink -> navigateTo(drink)  }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view).apply {

            recycler.adapter = adapter
            viewModel.state.observe(viewLifecycleOwner){state ->
                binding.progress.visibility =  if (state.loading) View.VISIBLE else View.GONE
                state.drinks?.let {
                    adapter.drinkList = state.drinks
                    adapter.notifyDataSetChanged()
                }
            }
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "DrinkList"
    }

    private fun navigateTo(drink: Drink) {
        findNavController().navigate(
            R.id.action_mainFragment_to_detailFragment,
            bundleOf(DetailFragment.EXTRA_DRINK to drink)
        )

    }
}





