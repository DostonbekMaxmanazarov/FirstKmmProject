package com.example.weatherappkmm.network.response.currency

import kotlinx.serialization.Serializable

@Serializable
data class Motd(
    val msg: String? = null,
    val url: String? = null
)