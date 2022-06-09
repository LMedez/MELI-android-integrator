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

/**
 * All the logic for the activity_suggestion.xml.
 */
class SuggestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuggestionBinding
    private val retrofit by lazy { ActicityServiceImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuggestionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Make the request for the API, and then update the View.
        callApi()

        binding.anotherBT.setOnClickListener {
            /**Here we decided that, every time the user want to "Try Another" (maybe because there
             * isn´t a suggestion for this quantity of participants ), would be desirable to choose
             * another type of activity that feet to this requirement.
             */
            finish()
        }
        binding.backIB.setOnClickListener{
            /**
             * Go abck to the previous activity (ActivitiesActivity )
             */
            finish()
        }
    }

    /**
     * Update the fields of the view with the received parameters.
     */
    fun updateView(priceRange: Double, countParticipant: Int, activityName: String, activityType: String){

        //Update the price tv
        var priceValue = ""
        when{
            priceRange > 0.6      -> priceValue = "High"
            priceRange > 0.3 && priceRange <= 0.6 -> priceValue = "Medium"
            priceRange > 0.0 && priceRange <= 0.3 -> priceValue = "Low"
            else     -> priceValue = "Free"
        }
        binding.priceResultTV.text = "$priceValue"

        //Update the participant quantity TV
        binding.participantsResultTV.text = countParticipant.toString()

        //Update the activity name TV
        binding.activityNameTV.text = activityName

        //Update the activity title TV
        if(!intent.hasExtra("activity")){
            binding.activityTypeTV.text = "Random"
            binding.randomActivityTV.isVisible = true
            binding.randomActivityTV.text = activityType
        }else {
            binding.randomActivityTV.isVisible = false
            binding.activityTypeTV.text = activityType
        }
    }

    /**
     * Consume the API with the parameters received:
     * NOTE That would be 4 types of query:
     *  With type of activity and participant quantity especified.
     *  Only with type off activity especified
     *  Only with participant quantity especified.
     *  Without any value especified.
     */
    fun callApi(){
        CoroutineScope(Dispatchers.IO).launch {
            lateinit var responseFromApi: ActivityModel
            when {
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
                else -> responseFromApi = retrofit.getActivityRandom()
            }
            //At Main Activity scope, the View is updated with the data from the API
            runOnUiThread {
                if(!responseFromApi.activity.isNullOrBlank()) {
                    updateView(responseFromApi.price, responseFromApi.participants, responseFromApi.activity,
                    responseFromApi.type)
                }else {
                    Snackbar.make(binding.root, "There isn´t a suggestion... look for another type of activity",
                        Snackbar.LENGTH_SHORT).show()
                    updateView(0.0, 0, "No activity found with the specified parameters", "Error")
                    binding.priceResultTV.visibility = View.GONE
                    binding.participantsResultTV.visibility = View.GONE
                }
            }
        }
    }
}