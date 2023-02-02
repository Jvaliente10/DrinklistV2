package com.example.joseantoniovaliente.drinklistv2.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.joseantoniovaliente.drinklistv2.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.nav_header_main.*

enum class ProviderType{
    BASIC
}

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUp((email ?: "") as String)
    }

    private fun setUp(email:String){
        emailUser.text=email
        title = "Pincipal"
        logOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }
}