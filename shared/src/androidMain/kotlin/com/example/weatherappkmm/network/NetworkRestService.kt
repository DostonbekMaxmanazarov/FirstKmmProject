package com.example.weatherappkmm.network

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import java.util.concurrent.TimeUnit


actual object NetworkRestService {

    /*Rest client*/
    actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
        config(this)

        engine {
            config {
                retryOnConnectionFailure(true)
                connectTimeout(5, TimeUnit.SECONDS)
            }
        }
    }

    actual fun initLogger() {
        Napier.base(DebugAntilog())
    }
}