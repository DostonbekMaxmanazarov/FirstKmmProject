package com.example.weatherappkmm.network

import io.ktor.client.*

expect object NetworkRestService {

    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient

    fun initLogger()
}