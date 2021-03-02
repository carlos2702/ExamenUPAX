package com.example.examenupax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.examenupax.data.ServicioUPAX
import kotlinx.android.synthetic.main.activity_menu_principal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MenuPrincipalActivity : AppCompatActivity() {

    val URL= "https://dl.dropboxusercontent.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        button2.setOnClickListener {
            this.startActivity( Intent(this, MostrarListaActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(
                                       Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }

        button3.setOnClickListener {
            this.startActivity(Intent(this,NuevoColaboradorActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(
                                       Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }

        val api = llamarServicio(URL).create(ApiRetrofit::class.java)

        var call = api.getService().enqueue(object : Callback<ServicioUPAX> {
            override fun onResponse(
                call: Call<ServicioUPAX>,
                response: Response<ServicioUPAX>
            ) {
                //recibir la respuesta del servicio
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext,"La respuesta de tu servicio es exitosa", Toast.LENGTH_LONG).show()
                    val respuesta= response.body()?.data
                    Log.i("TAG", "La respuesta es: $respuesta")
                    Toast.makeText(applicationContext, "respuesta: $respuesta", Toast.LENGTH_LONG).show()
                } else {
                    val body = response.body()
                    Toast.makeText(applicationContext,"respuesta: $body", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ServicioUPAX>, t: Throwable) {
                //recibir el error de la llamada del servicio
                val error = t.message
                Toast.makeText(applicationContext, "el error es: $error", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun llamarServicio(url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}