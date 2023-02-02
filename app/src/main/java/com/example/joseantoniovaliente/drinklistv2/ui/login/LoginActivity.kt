package com.example.joseantoniovaliente.drinklistv2.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.ui.home.HomeActivity
import com.example.joseantoniovaliente.drinklistv2.ui.home.ProviderType
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAnalytics = Firebase.analytics
        val bundle= Bundle()
        bundle.putString("message","Integración de firebase completa")
        firebaseAnalytics.logEvent("InitScreen", bundle)

        setUp()

    }

    private fun setUp(){
        title="Login"

        resgistrar.setOnClickListener{
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(),
                    password.text.toString()).addOnCompleteListener{
                        if ( it.isSuccessful){
                            it.result.user?.email?.let { it1 -> navHome(it1, ProviderType.BASIC) }
                        }else{
                            alertError()
                        }
                }
            }
        }

        login.setOnClickListener{
            if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.text.toString(),
                    password.text.toString()).addOnCompleteListener{
                    if ( it.isSuccessful){
                        it.result.user?.email?.let { it1 -> navHome(it1, ProviderType.BASIC) }
                    }else{
                        alertError()
                    }
                }
            }
        }



    }
    private fun alertError(){
        val alertError = AlertDialog.Builder(this)
        alertError.setTitle("Error")
        alertError.setMessage("Se ha producido un error con la autenticación del usuario")
        alertError.setPositiveButton("OK",null)
        val dialog: AlertDialog= alertError.create()
        dialog.show()

    }


    private fun navHome(email:String, providerType: ProviderType){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {

        }
        startActivity(homeIntent)
    }

}