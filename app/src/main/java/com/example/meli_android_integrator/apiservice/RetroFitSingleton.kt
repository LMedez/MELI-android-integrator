package com.example.meli_android_integrator.apiservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitSingleton {

    /**
     * Funsion para crear la instancia de retrofit.
     * Retorna un objeto Retrofit
     */
    fun getRetrofit() :Retrofit{

        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}