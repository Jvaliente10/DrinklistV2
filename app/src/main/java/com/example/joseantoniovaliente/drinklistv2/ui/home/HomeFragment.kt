package com.example.joseantoniovaliente.drinklistv2.ui.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentFormularioBinding
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.saludo.text="Hello " + FirebaseAuth.getInstance().currentUser?.email.toString()
    }


}