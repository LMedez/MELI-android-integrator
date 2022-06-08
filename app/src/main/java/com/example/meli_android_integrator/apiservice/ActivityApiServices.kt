package com.example.meli_android_integrator.apiservice

import com.example.meli_android_integrator.model.ActivityModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ActivityApiServices {

    /**
     * Funsion para obtener una actividad random
     */
    @GET("activity/")
    suspend fun getRandomActivity(): Response<ActivityModel>

    /**
     * Funsion para obtener una actividad segun el tipo
     */
    @GET
    suspend fun getActivityByType(@Url query: String): Response<ActivityModel>

    /**
     * Funsion para obtener una actividad segun el numero de participantes y el tipo de actividad
     */
    @GET
    suspend fun getActivityByNumberAndType(@Url query: String): Response<ActivityModel>

    /**
     * Funcion para obtener una actividad random segun el numero de participantes
     */
    @GET
    suspend fun getActivityByNumber(@Url query: String): Response<ActivityModel>

}