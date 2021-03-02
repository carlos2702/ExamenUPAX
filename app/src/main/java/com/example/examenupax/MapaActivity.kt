package com.example.examenupax

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.examenupax.data.Employee
import com.example.examenupax.data.Location
import com.example.examenupax.data.LocationX
import com.example.examenupax.util.Utils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MapaActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    val listLocation:ArrayList<LatLng> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        createFragment()
    }

    fun createFragment() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled= true

        if (map !=null){
            localizaciones()
        }
    }

    fun localizaciones() {
        val jsonFileString = Utils().getJsonDataFromAsset(applicationContext, "employees_data.json")
        Log.i("data", "respuesta json: $jsonFileString")
        val gson = Gson()
        val listPersonType = object : TypeToken<Location>() {}.type
        val location:Location = gson.fromJson(jsonFileString, listPersonType)
        location.data.employees.forEachIndexed { index, location ->
            val loc= LatLng(location.location.lat.toDouble(),location.location.log.toDouble())
            listLocation.add(loc)
            val marcador = MarkerOptions().position(loc).title(location.name)
            map.addMarker(marcador)
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(loc, 17f),
                4000,
                null
            )
        }
        location.data.employees.forEachIndexed { idx, person -> Log.i("data", "> Item $idx:\n$location") }

    }

}