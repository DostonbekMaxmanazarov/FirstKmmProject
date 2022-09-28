package com.example.weatherappkmm.network.response.weather

import kotlinx.serialization.Serializable

@Serializable
data class Main(
    val feels_like: Double? = null,
    val humidity: Int? = null,
    val pressure: Int? = null,
    val temp: Double? = null,
    val temp_max: Double? = null,
    val temp_min: Double? = null
)