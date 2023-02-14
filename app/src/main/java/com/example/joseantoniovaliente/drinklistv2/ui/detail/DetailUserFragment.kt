package com.example.joseantoniovaliente.drinklistv2.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentDetailBinding
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentDetailUserBinding
import com.example.joseantoniovaliente.drinklistv2.loadUrl
import com.example.joseantoniovaliente.drinklistv2.model.db.CocktailDb
import com.example.joseantoniovaliente.drinklistv2.ui.dialog.MyDialog
import kotlinx.android.synthetic.main.fragment_my_dialog.*

class DetailUserFragment : Fragment(R.layout.fragment_detail_user) {

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
        val binding = FragmentDetailUserBinding.bind(view)
        viewModel.details.observe(viewLifecycleOwner){ details ->
            val coctelDetail = details.details
            if (coctelDetail != null) {
                binding.imagen.loadUrl(coctelDetail.imagen)
                binding.nombre.text ="Name: "+ coctelDetail.nombre
                binding.category.text="Category: " + coctelDetail.category
                binding.glass.text="Glass: " + coctelDetail.glass
                binding.instructions.text ="Instructions: " + coctelDetail.instrucciones
                binding.ingredients.text="Ingredients: " + coctelDetail.ingredientes
                (requireActivity() as AppCompatActivity).supportActionBar?.title = coctelDetail.nombre
            }

        }
        binding.floatingActionButton2.setOnClickListener{
            MyDialog(
                onSubmitClickListener = { nombre->
                    if (nombre.isNotBlank()){
                        viewModel.updateCocktailName(nombre)
                    }

                }
            ).show(requireFragmentManager(),"Modificar Nombre")

        }
        binding.floatingActionButton.setOnClickListener{
            viewModel.deleteCocktail()

        }


    }
}