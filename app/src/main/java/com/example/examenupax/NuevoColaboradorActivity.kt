package com.example.examenupax

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examenupax.data.Employee
import com.example.examenupax.data.LocationX
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_nuevo_colaborador.*
import java.lang.Exception

class NuevoColaboradorActivity : AppCompatActivity() {

    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_colaborador)

        db = FirebaseDatabase.getInstance().getReference().child("data")

        buttonAgregar.setOnClickListener {
            agregarColaborador()
        }
    }


    private fun agregarColaborador() {
        val ide = et_id.text.toString()
        val nombre = et_user_name.text.toString()
        val latitud = et_latitud.text.toString()
        val longitud = et_longitud.text.toString()
        val correo = et_correo.text.toString()
        if (!ide.isEmpty() && !nombre.isEmpty() && !latitud.isEmpty() && !longitud.isEmpty() && !correo.isEmpty()) {
            try {
                val locacion = LocationX(latitud, longitud)
                val colaborador = Employee(
                    ide,
                    locacion,
                    correo,
                    nombre
                )
                db.child("employees").push().setValue(colaborador)
                    .addOnSuccessListener { void: Void? ->
                        Toast.makeText(
                            this,
                            "Tu Colaborador se ha agregado correctamente a la base de datos",
                            Toast.LENGTH_LONG
                        ).show()
                    }.addOnFailureListener { exception: Exception ->
                        Toast.makeText(this, exception.toString(), Toast.LENGTH_LONG).show()
                    }
            } catch (e: Exception) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Por favor ingresa los datos", Toast.LENGTH_LONG).show()
        }
    }
}






