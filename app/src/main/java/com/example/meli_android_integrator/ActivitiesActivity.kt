package com.example.meli_android_integrator

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.meli_android_integrator.databinding.ActivityActivitiesBinding
import com.example.meli_android_integrator.databinding.ActivityTermsBinding
import com.example.meli_android_integrator.databinding.CustomListviewBinding
import java.util.*

class ActivitiesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityActivitiesBinding

    /*
    * List of activities
    *
    * */
    private val activities = listOf(
        "Education",
        "Recreational",
        "Social",
        "Diy",
        "Charity",
        "Cooking",
        "Relaxation",
        "Music",
        "Busywork"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivitiesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val countParticipants = intent.getIntExtra("count_participants", 1)

        // Set the adapter of list view and perform the lambda for the onClick method
        binding.activitiesLV.adapter = Adapter(this, activities) { activity ->
            // Navigate to activity with the selected activity and the count of participant
            navigateToSuggestionActivity(activity, countParticipants)
        }

        binding.randomIB.setOnClickListener {
            // Navigate to activity with a random activity and the count of participant
            navigateToSuggestionActivity(countParticipants = countParticipants)
        }
    }

    private fun navigateToSuggestionActivity(
        activity: String = activities.random().lowercase(),
        countParticipants: Int
    ) {
        val intentActivitiesActivity = Intent(this, SuggestionActivity::class.java).apply {
            putExtra("count_participants", countParticipants)
            putExtra("activity", activity)
        }
        startActivity(intentActivitiesActivity)
    }

    /*
    * Adapter for list view. Extends of a base ArrayAdapter and override the getView method to return
    * a custom view
    *
    */
    inner class Adapter(
        context: Context,
        private val activities: List<String>,
        private val onItemClick: (String) -> Unit
    ) : ArrayAdapter<String>(context, R.layout.custom_listview, activities) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val binding =
                CustomListviewBinding.inflate(LayoutInflater.from(context), null, false)
            binding.activityTV.text = activities[position]
            binding.suggestionIB.setOnClickListener { onItemClick(activities[position].lowercase()) }
            return binding.root
        }
    }
}