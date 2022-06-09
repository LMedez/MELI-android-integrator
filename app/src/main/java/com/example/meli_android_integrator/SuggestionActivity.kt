package com.example.meli_android_integrator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.meli_android_integrator.apiservice.ActicityServiceImpl
import com.example.meli_android_integrator.databinding.ActivitySuggestionBinding
import com.example.meli_android_integrator.databinding.ActivityTermsBinding
import com.example.meli_android_integrator.model.ActivityModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.zip.ZipEntry

class SuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding
    private val retrofit by lazy { ActicityServiceImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val countParticipants = intent.getIntExtra("count_participants", 0)        //Usamos 0 pa no poner null?
//        val activityType = intent.getStringExtra("activity")                                  //Puede no haber nada?

/*        if(intent.hasExtra("count_participants")){
            val countParticipants = intent.getIntExtra("count_participants", 0)
        }
        if(intent.hasExtra("activity")){
            val activityType = intent.getStringExtra("activity")
        }
*/
        /*binding.testAPI.setOnClickListener {

    CoroutineScope(Dispatchers.IO).launch {
        val responseAPIRandom = ActicityServiceImpl()
        val activityRandom = responseAPIRandom.getActivityRandom()
        Log.e("ActivityMainRandom", activityRandom.toString())

        val activityByType = ActicityServiceImpl().getActivityByType("cooking")
        Log.e("ActivityMain", activityByType.toString())

        val activityByNumber = ActicityServiceImpl().getActivityByNumber(2)
        Log.e("ActivityByNumber", activityByNumber.toString())

        val activityByNumberAndType = ActicityServiceImpl().getActivityByNumberAndType("cooking", 1)
        Log.e("NumerAndType", activityByNumberAndType.toString())

    }

}*/
        callApi()
//        Snackbar.make(view, "Participantes = $countParticipants / tipo activity = $activityType", Snackbar.LENGTH_SHORT).show()

        //Llamar a API con los parametros recibidos.
            //Si tenemos activityType y countParticipants
                //queryApi(activityType ,countParticipants )
            //Si tenemos activityType
                //queryApi(activityType)
            //Si tenemos countParticipants
                //queryApi(countParticipants)
/*
        updateView(responseFromApi.price, responseFromApi.participants, responseFromApi.activity,
            responseFromApi.type)
*/
        binding.anotherBT.setOnClickListener {
            //Llamar devuelta a la API.. tener en cuenta si era random y eso. Usamos sharedpref para guardar estos datos?
            //Vamos a necesitar algo q guarde si era random o no
//            callApi()
            finish()
        }
        binding.backIB.setOnClickListener{
            finish()
        }
    }

    //Update the fields of the view with the received parameters.
    fun updateView(priceRange: Double, countParticipant: Int, activityName: String, activityType: String){

        //Actualizar dato con precio q viene de la API
        var priceValue = ""
        when{
            priceRange > 0.6      -> priceValue = "High"
            priceRange > 0.3 && priceRange <= 0.6 -> priceValue = "Medium"
            priceRange > 0.0 && priceRange <= 0.3 -> priceValue = "Low"
            else     -> priceValue = "Free"
        }
        binding.priceResultTV.text = "$priceValue"

        //Actualizar dato con cant participantes q viene de la API
        binding.participantsResultTV.text = countParticipant.toString()

        //Actualizar dato titulo activity con lo q q viene de la API
        binding.activityNameTV.text = activityName

        //Si viene tipo actividad como parametro-> Actualizar titulo toolbar con este, o el de la API.
        if(!intent.hasExtra("activity")){
            binding.activityTypeTV.text = "Random"
            binding.randomActivityTV.isVisible = true
            binding.randomActivityTV.text = activityType
        }else {
            binding.randomActivityTV.isVisible = false
            binding.activityTypeTV.text = activityType
        }
    }

    fun callApi(){
        CoroutineScope(Dispatchers.IO).launch {
            lateinit var responseFromApi: ActivityModel
            when {                                                                                  //Esta bien lo del let
                intent.hasExtra("count_participants") &&
                        intent.hasExtra("activity") -> intent.getStringExtra("activity")?.let {
                    responseFromApi = retrofit.getActivityByNumberAndType(it, intent.getIntExtra("count_participants", 0)
                    )
                }

                intent.hasExtra("activity") &&
                        !intent.hasExtra("count_participants") -> intent.getStringExtra("activity")
                    ?.let { responseFromApi = retrofit.getActivityByType(it)
                    }

                !intent.hasExtra("activity") &&
                        intent.hasExtra("count_participants") -> responseFromApi = retrofit.getActivityByNumber(
                    intent.getIntExtra("count_participants", 0)
                )

//            !intent.hasExtra("activity") && !intent.hasExtra("count_participants")-> queryApi()
                else -> responseFromApi = retrofit.getActivityRandom()
            }
            runOnUiThread {
                if(!responseFromApi.activity.isNullOrBlank()) {
                    updateView(responseFromApi.price, responseFromApi.participants, responseFromApi.activity,
                    responseFromApi.type)
                }else {
                    Snackbar.make(binding.root, "There isn´t a suggestion... Try another.",
                        Snackbar.LENGTH_SHORT).show()
                    updateView(0.0, 0, "No activity found with the specified parameters", "Error")
                    binding.priceResultTV.visibility = View.GONE
                    binding.participantsResultTV.visibility = View.GONE
                }
            }
        }
    }
}