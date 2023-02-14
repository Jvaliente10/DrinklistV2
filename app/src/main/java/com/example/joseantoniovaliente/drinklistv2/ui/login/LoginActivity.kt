package com.example.joseantoniovaliente.drinklistv2.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.joseantoniovaliente.drinklistv2.R
import com.example.joseantoniovaliente.drinklistv2.ui.main.MainActivity
import com.example.joseantoniovaliente.drinklistv2.ui.main.ProviderType
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private val db = FirebaseFirestore.getInstance()


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

        resgistrar.setOnClickListener{if (email.text.isNotEmpty() && password.text.isNotEmpty()){
            // Primero se comprueba si ya existe un usuario con ese correo electrónico
            db.collection("usuarios").whereEqualTo("email", email.text.toString()).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Si se encontró un documento con ese correo electrónico, significa que ya existe un usuario con ese correo
                    if (!task.result!!.isEmpty) {
                    } else {
                        // Si no existe un usuario con ese correo electrónico, se procede a registrar al nuevo usuario
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener {
                            if ( it.isSuccessful){
                                it.result.user?.email?.let { it1 ->
                                    // Guardar el usuario en la colección "usuarios"
                                    val usuario = hashMapOf(
                                        "provider" to ProviderType.BASIC.toString()
                                    )
                                    db.collection("usuarios").document(it1).set(usuario).addOnSuccessListener {
                                        navHome(it1, ProviderType.BASIC)
                                    }
                                }
                            } else {
                                alertAuthError()
                            }
                        }
                    }
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
                        alertAuthError()
                    }
                }
            }
        }



    }
    private fun alertAuthError(){
        val alertError = AlertDialog.Builder(this)
        alertError.setTitle("Error")
        alertError.setMessage("Se ha producido un error con la autenticación del usuario")
        alertError.setPositiveButton("OK",null)
        val dialog: AlertDialog= alertError.create()
        dialog.show()
    }


    private fun navHome(email:String, providerType: ProviderType){
        val homeIntent = Intent(this, MainActivity::class.java).apply {

        }
        homeIntent.putExtra("email", email)
        startActivity(homeIntent)

    }

}