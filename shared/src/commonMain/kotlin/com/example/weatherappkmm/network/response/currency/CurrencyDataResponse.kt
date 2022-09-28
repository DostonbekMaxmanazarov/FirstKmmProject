package com.example.weatherappkmm.network.response.currency

import kotlinx.serialization.Serializable

@Serializable
data class CurrencyDataResponse(
    val date: String? = null,
    val historical: Boolean? = null,
    val info: Info? = null,
    val motd: Motd? = null,
    val query: Query? = null,
    val result: Double? = null,
    val success: Boolean? = null
)