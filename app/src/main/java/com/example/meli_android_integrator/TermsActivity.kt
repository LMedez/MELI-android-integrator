package com.example.meli_android_integrator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.meli_android_integrator.databinding.ActivityTermsBinding

class TermsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTermsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.closeIB.setOnClickListener {
            navigateToMainActivity()
        }
    }

    fun navigateToMainActivity() {
        finish()
    }
}