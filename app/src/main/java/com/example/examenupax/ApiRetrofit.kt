package com.example.examenupax

import com.example.examenupax.data.ServicioUPAX
import retrofit2.Call
import retrofit2.http.GET

interface ApiRetrofit {
    @GET("s/5u21281sca8gj94/getFile.json?dl=0")
    fun getService(): Call<ServicioUPAX>
}