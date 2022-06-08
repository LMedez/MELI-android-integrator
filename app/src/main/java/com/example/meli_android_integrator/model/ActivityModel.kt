package com.example.meli_android_integrator.model

/**
 * Modelo de datos que representa la respuesta del consumo
 */
data class ActivityModel(
    val accessibility: Double = 0.0,
    val activity: String = "",
    val key: String = "",
    val link: String = "",
    val participants: Int = 0,
    val price: Double = 0.0,
    val type: String = ""
)