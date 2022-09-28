package com.example.weatherappkmm.network.api_service

import com.example.weatherappkmm.Platform
import com.example.weatherappkmm.enum.UrlType
import com.example.weatherappkmm.network.NetworkRestService.initLogger
import com.example.weatherappkmm.network.NetworkRestService.httpClient
import io.github.aakira.napier.Napier
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ApiService {

    val httpClient = httpClient {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HTTP Client", message = message)
                }
            }
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
                encodeDefaults = false
            })
        }

        install(HttpTimeout)
        {
            requestTimeoutMillis = 3000
        }
    }
        .also { initLogger() }

    suspend inline fun <reified T> create(
        httpMethod: HttpMethod,
        url: String,
        headers: Array<Pair<String, String>>?,
        queries: Array<Pair<String, Any>>?,
        httpBody: Any?,
        type: UrlType
    ): T? {
        val platform = Platform()
        val response = httpClient.request<HttpResponse> {

            when (type) {
                UrlType.NEWS_TYPE -> {
                    url(Api.BASE_URL_NEWS_API)
                }

                UrlType.WEATHER_TYPE -> {
                    url(Api.BASE_URL_WEATHER_API)
                }

                UrlType.CURRENCY_TYPE -> {
                    url(Api.BASE_URL_CURRENCY_API)
                }
            }

            method = httpMethod

            url {

            }

            headers {
                append("Accept", "application/json")
                append("Content-Type", "application/json")
                append("device_os", platform.platformName)
                append("api_version", platform.appVersionCode)
                append("language", platform.appLanguage)

                if (headers != null) {
                    for (header in headers) {
                        append(header.first, header.second)
                    }
                }

            }

            if (queries != null)
                Parameters.build {
                    queries.forEach {
                        parameter(it.first, it.second)

                    }
                }

            httpBody?.let {
                body = httpBody
            }
        }

        return receiveResponse<T>(response)
    }

    suspend inline fun <reified T> receiveResponse(response: HttpResponse): T? {
        if (response.status.isSuccess()) {
            return response.receive()
        } else {
            throw convertToProperException(response)
        }
    }

    fun convertToProperException(httpResponseData: HttpResponse): Exception {
        val responseCode = httpResponseData.status.value
        val errorMessage = httpResponseData.toString()

        return when (responseCode) {
            400 -> BadRequestException(errorMessage)
            401 -> UnauthorisedException(errorMessage)
            402 -> PaymentRequiredException(errorMessage)
            403 -> ForbiddenException(errorMessage)
            404 -> NotFoundException(errorMessage)
            405 -> MethodNotAllowedException(errorMessage)

            500 -> InternalServerErrorException(errorMessage)
            501 -> NotImplementedException(errorMessage)
            502 -> BadGatewayException(errorMessage)
            503 -> ServiceUnavailableException(errorMessage)
            else -> ApiException(errorMessage)
        }
    }

}

fun convertToProperException(httpResponseData: HttpResponse): Exception {
    val responseCode = httpResponseData.status.value
    val errorMessage = httpResponseData.toString()

    return when (responseCode) {
        400 -> BadRequestException(errorMessage)
        401 -> UnauthorisedException(errorMessage)
        402 -> PaymentRequiredException(errorMessage)
        403 -> ForbiddenException(errorMessage)
        404 -> NotFoundException(errorMessage)
        405 -> MethodNotAllowedException(errorMessage)

        500 -> InternalServerErrorException(errorMessage)
        501 -> NotImplementedException(errorMessage)
        502 -> BadGatewayException(errorMessage)
        503 -> ServiceUnavailableException(errorMessage)
        else -> ApiException(errorMessage)
    }
}

open class ApiException(errorMessage: String?) : RuntimeException(errorMessage)

class BadRequestException(errorMessage: String?) : ApiException(errorMessage)

class UnauthorisedException(errorMessage: String?) : ApiException(errorMessage)

class PaymentRequiredException(errorMessage: String?) : ApiException(errorMessage)

class ForbiddenException(errorMessage: String?) : ApiException(errorMessage)

class NotFoundException(errorMessage: String?) : ApiException(errorMessage)

class MethodNotAllowedException(errorMessage: String?) : ApiException(errorMessage)

class InternalServerErrorException(errorMessage: String?) : ApiException(errorMessage)

class NotImplementedException(errorMessage: String?) : ApiException(errorMessage)

class BadGatewayException(errorMessage: String?) : ApiException(errorMessage)

class ServiceUnavailableException(errorMessage: String?) : ApiException(errorMessage)
