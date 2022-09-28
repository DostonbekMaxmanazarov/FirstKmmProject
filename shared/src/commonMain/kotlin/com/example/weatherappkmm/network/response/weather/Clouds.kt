package com.example.weatherappkmm.network.response.weather

import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int? = null
)