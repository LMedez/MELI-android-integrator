package com.example.meli_android_integrator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.meli_android_integrator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.startBT.setOnClickListener {
            //Validar participantes ingresados
            if (binding.participantCountET.text.toString() == "") {
                //Llamar a ActivitiesActivity sin cant participantes.
                   val intentActivitiesActivity = Intent(this, ActivitiesActivity::class.java)
                    startActivity(intentActivitiesActivity)
            } else {
                val countParticipants = binding.participantCountET.text.toString().toInt()
                //Llamar a ActivitiesActivity con cantidad de participantes.
                   val intentActivitiesActivity = Intent(this, ActivitiesActivity::class.java).apply {
                        putExtra("count_participants", countParticipants)
                    }
                    startActivity(intentActivitiesActivity)
            }

        }

        binding.participantCountET.doOnTextChanged { text, start, before, count ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            if (text.isNullOrBlank() || text.startsWith("0")){
                binding.startBT.isEnabled = false
                Snackbar.make(view, "Invalid participant quantity.",
                    Snackbar.LENGTH_SHORT).show()
            }else{
                binding.startBT.isEnabled = true

            }
        }

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
    }

    //Called when the title "Terms and condition" is clicked.
    fun termsOnClickListener(view: View) {
        //Llamar a TermsActivity sin parametros
        startActivity(Intent(this, TermsActivity::class.java))
    }
}