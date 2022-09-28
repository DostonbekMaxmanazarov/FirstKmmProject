package com.example.weatherappkmm.network.response.weather

import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    val deg: Int? = null,
    val speed: Double? = null
)