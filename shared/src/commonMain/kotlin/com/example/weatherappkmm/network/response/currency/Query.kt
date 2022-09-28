package com.example.weatherappkmm.network.response.currency

import kotlinx.serialization.Serializable

@Serializable
data class Query(
    val amount: Int? = null,
    val from: String? = null,
    val to: String? = null
)