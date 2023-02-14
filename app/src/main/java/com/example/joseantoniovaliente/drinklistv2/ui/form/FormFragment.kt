package com.example.joseantoniovaliente.drinklistv2.ui.form


import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentFormularioBinding
import com.example.joseantoniovaliente.drinklistv2.model.Drink
import com.example.joseantoniovaliente.drinklistv2.model.db.CocktailDb
import com.example.joseantoniovaliente.drinklistv2.ui.detail.DetailFragment
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File


class FormFragment : Fragment(R.layout.fragment_formulario) {
    private val formViewModel: FormViewModel by viewModels()
    private lateinit var binding: FragmentFormularioBinding
    var selectedImageUri: Uri? = null
    val storageRef = FirebaseStorage.getInstance().reference
    var imagesRef: StorageReference? = storageRef.child("images")

    val pickmedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
        if (uri!=null){
            selectedImageUri = uri
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFormularioBinding.bind(view)

        binding.loadImage.setOnClickListener {
            pickmedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.addCocktail.setOnClickListener {
            if (binding.nameForm.text.toString()
                    .isBlank() || binding.ingredientsForms.text.toString()
                    .isBlank() || binding.instructionsForms.text.toString().isBlank()
            ) {
                val alert = AlertDialog.Builder(requireContext())
                alert.setTitle("Error")
                alert.setMessage("Todos los campos son obligatorios, excepto la imagen")
                alert.setPositiveButton("Aceptar") { dialog, _ ->
                    dialog.dismiss()
                }
                alert.show()
            } else {
                if (selectedImageUri == null) {
                    // Se guarda el coctel sin imagen
                    val coctel = CocktailDb(
                        binding.nameForm.text.toString(), binding.ingredientsForms.text.toString(),
                        binding.instructionsForms.text.toString(), ""
                    )
                    formViewModel.createCoctel(coctel)
                    navigateTo()
                } else {
                    val fileReference = imagesRef?.child(selectedImageUri?.lastPathSegment!!)
                    fileReference?.putFile(selectedImageUri!!)
                        ?.addOnSuccessListener {
                            fileReference.downloadUrl.addOnSuccessListener {
                                val coctel = CocktailDb(
                                    binding.nameForm.text.toString(),
                                    binding.categoryForm.text.toString(),
                                    binding.glassForm.text.toString(),
                                    binding.ingredientsForms.text.toString(),
                                    binding.instructionsForms.text.toString(),
                                    it.toString()
                                )
                                formViewModel.createCoctel(coctel)
                                navigateTo()



                            }
                        }
                }
            }
        }
    }
    private fun navigateTo() {
        findNavController().navigate(R.id.action_formFragment_to_homeFragment)

    }
}
