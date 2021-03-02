package com.example.examenupax

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenupax.data.*
import com.example.examenupax.util.Utils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_menu_principal.*
import kotlinx.android.synthetic.main.activity_mostrar_lista.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MostrarListaActivity : AppCompatActivity() {

    val URL= "https://dl.dropboxusercontent.com/"
    private val codigodatos = 1
    private var recyclerView: RecyclerView? = null
    private var myappAdapter: MyAppAdapter? = null
    var lista :ArrayList<Employee> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_lista)

        // A mi Adaptador le paso mi modelo
        myappAdapter = MyAppAdapter(applicationContext,lista)
        // Le paso mi adaptador al RecyclerView

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView?.setHasFixedSize(true)
        val layoutManager= LinearLayoutManager(this)
        recyclerView?.layoutManager=layoutManager
        recyclerView?.adapter = myappAdapter
        myappAdapter!!.notifyDataSetChanged()

        listaEmpleados()

        buttonMapa.setOnClickListener {
            this.startActivity( Intent(this, MapaActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    }

    fun listaEmpleados() {
        val jsonFileString = Utils().getJsonDataFromAsset(applicationContext, "employees_data.json")
        Log.i("data", "respuesta json: $jsonFileString")
        val gson = Gson()
        val listPersonType = object : TypeToken<Location>() {}.type
        val location: Location = gson.fromJson(jsonFileString, listPersonType)
        location.data.employees.forEachIndexed { index, item ->
            val empleado = Employee(item.id, item.location, item.mail, item.name)
            lista.add(empleado)
            recyclerView?.adapter= myappAdapter
        }
        location.data.employees.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$location") }

    }

}
