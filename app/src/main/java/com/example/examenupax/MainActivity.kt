package com.example.examenupax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        button.setOnClickListener {
            firebaseAuthWithGoogle()
        }

    }

    private fun firebaseAuthWithGoogle() {
        val email= et_nombre_usuario.text.toString()
        val contrasena= et_contrasena.text.toString()
        if(et_contrasena.text.toString().isNotEmpty() && et_nombre_usuario.text.toString().isNotEmpty()){
            mAuth.signInWithEmailAndPassword(email, contrasena)
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "te conectaste correctamente", Toast.LENGTH_LONG).show()
                        this.startActivity( Intent(this, MenuPrincipalActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(
                            Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    }else{
                        Toast.makeText(this, "error de datos verifica tu email o contraseña", Toast.LENGTH_LONG).show()
                    }
                    button.isEnabled=true
                }
        }else{
            Toast.makeText(this, "Ingresa datos campos vacios", Toast.LENGTH_LONG).show()
            button.isEnabled=true
        }

    }
}