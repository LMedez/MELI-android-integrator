package com.example.meli_android_integrator.model

/**
 * Modelo de datos que representa la respuesta del consumo
 */
data class ActivityModel(
    val accessibility: Double,
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Double,
    val type: String
)