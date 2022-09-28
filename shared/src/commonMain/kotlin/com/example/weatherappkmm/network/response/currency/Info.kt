package com.example.weatherappkmm.network.response.currency

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val rate: Double? = null
)