package com.example.weatherappkmm.network.response.weather

import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    val lat: Double? = null,
    val lon: Double? = null
)