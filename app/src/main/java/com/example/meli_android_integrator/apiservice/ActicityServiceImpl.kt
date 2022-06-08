package com.example.meli_android_integrator.apiservice

import com.example.meli_android_integrator.model.ActivityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Clase para intanciar los metodos del api services
 */
class ActicityServiceImpl {


    private val retrofitResponse = RetroFitSingleton.getRetrofit()

    /**
     * Funsion que obtiene con coroutines una actividad random
     * Retorna un objeto ActivityModel
     */
    suspend fun getActivityRandom():ActivityModel{

        return withContext(Dispatchers.IO){
            val response = retrofitResponse.create(ActivityApiServices::class.java).getRandomActivity()
            return@withContext if(response.isSuccessful){
                response.body()!!
            }else{
                val emptyActivity = ActivityModel(0.0,"","","",0,0.0,"")
                emptyActivity
            }
        }
    }

    /**
     * Funsion que obtiene con coroutines una actividad segun el tipo
     * Retorna un objeto ActivityModel
     */
    suspend fun getActivityByType(query: String): ActivityModel {
        return withContext(Dispatchers.IO){
            val response = retrofitResponse.create(ActivityApiServices::class.java).getActivityByType("activity?type=${query}")
            return@withContext if(response.isSuccessful){
                response.body()!!
            }else{
                val emptyActivity = ActivityModel(0.0,"","","",0,0.0,"")
                emptyActivity
            }
        }

    }

    /**
     * Funsion que obtiene con coroutines una actividad segun el numero de participantes
     * Retorna un objeto ActivityModel
     */
    suspend fun getActivityByNumber(number: Int): ActivityModel{
        return withContext(Dispatchers.IO){
            val response = retrofitResponse.create(ActivityApiServices::class.java).getActivityByNumber("activity?participants=${number}")
            return@withContext if(response.isSuccessful){
                response.body()!!
            }else{
                val emptyActivity = ActivityModel(0.0,"","","",0,0.0,"")
                emptyActivity
            }
        }
    }

    /**
     * Funsion que obtiene con coroutines una actividad segun el tipo y el numero de participantes
     * Retorna un objeto ActivityModel
     */
    suspend fun getActivityByNumberAndType(query: String, number: Int): ActivityModel{
        return withContext(Dispatchers.IO){
            val response = retrofitResponse.create(ActivityApiServices::class.java).getActivityByNumberAndType("activity?type=${query}&participants=${number}")
            return@withContext if(response.isSuccessful){
                response.body()!!
            }else{
                val emptyActivity = ActivityModel(0.0,"","","",0,0.0,"")
                emptyActivity
            }
        }
    }

}