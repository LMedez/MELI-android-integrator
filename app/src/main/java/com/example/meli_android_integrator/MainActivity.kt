package com.example.meli_android_integrator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.example.meli_android_integrator.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
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
            if (binding.participantCountET.text.toString().equals("")) {                            //Tuve q poner toString porq no me tomaba el equals
                //Llamar a ActivitiesActivity sin cant participantes.
/*
                   val intentActivitiesActivity = Intent(this, ActivitiesActivity::class.java).apply
                    startActivity(intentActivitiesActivity)
*/

            } else {
                val countParticipants = binding.participantCountET.text.toString().toInt()          //Es necesrio?, se podria manejar solo con string?
                //Llamar a ActivitiesActivity con cantidad de participantes.
/*
                   val intentActivitiesActivity = Intent(this, ActivitiesActivity::class.java).apply {
                        putExtra("count_participants", countParticipants)
                    }
                    startActivity(intentActivitiesActivity)
*/
            }

        }

        binding.participantCountET.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrBlank()){
                binding.startBT.isEnabled = false                                                   //Como es al principio?
                Snackbar.make(view, "Invalid participant quantity.",
                    Snackbar.LENGTH_SHORT).show()
            }else{
                binding.startBT.isEnabled = true

            }
        }
    }

    //Called when the title "Terms and condition" is clicked.
    fun termsOnClickListener(view: View) {
        //Llamar a TermsActivity sin parametros
        startActivity(Intent(this, TermsActivity::class.java).apply {})
    }
}