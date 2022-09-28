package com.example.weatherappkmm.network

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*

actual object NetworkRestService {

    actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient {
        config(this)

        engine {
            /*configureRequest {
                setAllowsCellularAccess(true)
            }*/
        }
    }

    actual fun initLogger() = Napier.base(DebugAntilog())
}