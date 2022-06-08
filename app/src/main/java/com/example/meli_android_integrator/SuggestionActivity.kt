package com.example.meli_android_integrator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meli_android_integrator.databinding.ActivitySuggestionBinding
import com.example.meli_android_integrator.databinding.ActivityTermsBinding
import com.google.android.material.snackbar.Snackbar
import java.util.zip.ZipEntry

class SuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val countParticipants = intent.getIntExtra("count_participants", 0)        //Usamos 0 pa no poner null?
//        val activityType = intent.getStringExtra("activity")                                  //Puede no haber nada?

        if(intent.hasExtra("count_participants")){
            val countParticipants = intent.getIntExtra("count_participants", 0)
        }
        if(intent.hasExtra("activity")){
            val activityType = intent.getStringExtra("activity")
        }

/*        when{
            intent.hasExtra("count_participants") &&
                    intent.hasExtra("activity") -> queryApi(intent.getStringExtra("activity"),
                                                            intent.getIntExtra("count_participants", 0))

            intent.hasExtra("activity") &&
                    !intent.hasExtra("count_participants")-> queryApi(intent.getStringExtra("activity"))

            !intent.hasExtra("activity") &&
                    intent.hasExtra("count_participants")-> queryApi(intent.getIntExtra("count_participants", 0))

//            !intent.hasExtra("activity") && !intent.hasExtra("count_participants")-> queryApi()
            else queryApi()
        }
*///        Snackbar.make(view, "Participantes = $countParticipants / tipo activity = $activityType", Snackbar.LENGTH_SHORT).show()

        //Llamar a API con los parametros recibidos.
            //Si tenemos activityType y countParticipants
                //queryApi(activityType ,countParticipants )
            //Si tenemos activityType
                //queryApi(activityType)
            //Si tenemos countParticipants
                //queryApi(countParticipants)

        updateView(0.1F,2,"bascket","deporte")

        binding.anotherBT.setOnClickListener {
            //Llamar devuelta a la API.. tener en cuenta si era random y eso. Usamos sharedpref para guardar estos datos?
            //Vamos a necesitar algo q guarde si era random o no
            queryApi(null, null)
            updateView(0.1F,2,"bascket","deporte")
        }
        binding.backIB.setOnClickListener{
                finish()
        }
    }
    //Request the info to the API
    fun queryApi(activityType: String?, countParticipant: Int?){
        when{
            activityType.isNullOrBlank() -> {}
            countParticipant == null -> {}
        }
        val query = ""
    }
    //Update the fields of the view with the received parameters.
    fun updateView(priceRange: Float, countParticipant: Int, activityName: String, activityType: String){

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
        if(activityType.equals("")){
            binding.activityTypeTV.text = "Random"
        }else {
            binding.activityTypeTV.text = activityType
        }
    }

}