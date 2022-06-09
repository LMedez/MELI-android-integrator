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

        /**
         * On click Button Start check if exists participants in the edit text
         * and start the activity with extras of participant
         * otherwise, start the activity without extras
         *
         * */
        binding.startBT.setOnClickListener {
<<<<<<< HEAD
            if (binding.participantCountET.text.toString() == "") {
                navigateToActivitiesActivity()
            } else {
                val countParticipants = binding.participantCountET.text.toString().toInt()
                navigateToActivitiesActivity(countParticipants)
=======
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
>>>>>>> 33f6e134d8fd83143f60a2b06dab7f6af1fdc951
            }
        }

<<<<<<< HEAD
        /**
         * Check if the number of participants is 0 or starts with 0
         * and disables the start button, otherwise enables the button
         *
         * */
        binding.participantCountET.doOnTextChanged { text, _, _, _ ->
            if (text?.startsWith("0") == true) {
                binding.startBT.isEnabled = false
                Snackbar.make(
                    view, "Invalid participant quantity.",
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
=======
        binding.participantCountET.doOnTextChanged { text, start, before, count ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            if (text.isNullOrBlank() || text.startsWith("0")){
                binding.startBT.isEnabled = false
                Snackbar.make(view, "Invalid participant quantity.",
                    Snackbar.LENGTH_SHORT).show()
            }else{
>>>>>>> 33f6e134d8fd83143f60a2b06dab7f6af1fdc951
                binding.startBT.isEnabled = true
            }
        }
    }

    /**
     * Called when the title "Terms and condition" is clicked.
     *
     * */
    fun termsOnClickListener(view: View) {
        startActivity(Intent(this, TermsActivity::class.java))
    }

    /**
     * Navigate to second activity and add the participants in the intent extras
     * or without extras if participants is empty
     *
     * */
    private fun navigateToActivitiesActivity(participant: Int? = null) {
        val intentActivitiesActivity = Intent(this, ActivitiesActivity::class.java)
        participant?.let {
            intentActivitiesActivity.apply {
                putExtra("count_participants", it)
            }
        }
        startActivity(intentActivitiesActivity)
    }
}