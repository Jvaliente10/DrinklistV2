package com.example.joseantoniovaliente.drinklistv2.ui.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.databinding.FragmentMyDialogBinding

class MyDialog(private val onSubmitClickListener: (String) -> Unit) : DialogFragment(R.layout.fragment_my_dialog) {
        private lateinit var binding : FragmentMyDialogBinding
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
                binding = FragmentMyDialogBinding.inflate(LayoutInflater.from(context))
                val builder = AlertDialog.Builder(requireActivity())
                builder.setView(binding.root)
                binding.modifyAccept.setOnClickListener{
                        onSubmitClickListener.invoke(binding.nameForm.text.toString())
                        dismiss()
                }
                val dialog = builder.create()
                return dialog

        }



}

