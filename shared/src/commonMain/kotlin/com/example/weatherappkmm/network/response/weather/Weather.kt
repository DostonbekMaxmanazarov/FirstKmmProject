package com.example.weatherappkmm.network.response.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("description")
    val descriptionTemp: String? = null,
    val icon: String? = null,
    val id: Int? = null,
    val main: String? = null
)