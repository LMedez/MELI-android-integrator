package com.example.meli_android_integrator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.meli_android_integrator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.startBT.setOnClickListener {
            //Validar participantes ingresados

            try {
                val countParticipants = binding.participantCountET.text.toString().toInt()          //Es necesrio?, se podria manejar solo con string?
                if(countParticipants > 0 ){                             //El == null esta feo, ver como se escribe bien.
                    //Llamar a ActivitiesActivity con cantidad de participantes.
/*
                   val intentActivitiesActivity = Intent(this, ActivitiesActivity::class.java).apply {
                        putExtra("count_participants", countParticipants)
                    }
                    startActivity(intentActivitiesActivity)
*/
                }else {
                    Snackbar.make(view, "Invalid participant quantity.",
                        Snackbar.LENGTH_SHORT).show()
                }
            }catch (e: NumberFormatException) {                                                     //esta bien hacerlo con try catch??
                if(binding.participantCountET.text.equals(""))
                //Mostrar snackbar error. "no es un nro."
                    Snackbar.make(view, "Not a valid number.",
                        Snackbar.LENGTH_SHORT).show()
                //Llamar a ActivitiesActivity sin cant participantes.           //Debe haber alguna forma de unnificarlo con el de arriba y q qde mas lindo.
/*
                   val intentActivitiesActivity = Intent(this, ActivitiesActivity::class.java).apply
                    startActivity(intentActivitiesActivity)
*/

            }


        }
    }

    //Called when the title "Terms and condition" is clicked.
    fun termsOnClickListener(view: View) {
        //Llamar a TermsActivity sin parametros
        startActivity(Intent(this, TermsActivity::class.java).apply {})
    }
}