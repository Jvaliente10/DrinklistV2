package com.example.joseantoniovaliente.drinklistv2.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.joseantoniovaliente.drinklistv2.R

import kotlinx.android.synthetic.main.fragment_formulario.*

class FormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_formulario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        load_image.setOnClickListener {

        }

        accept.setOnClickListener {

        }
    }
}
