package com.example.weatherappkmm.repository

import com.example.weatherappkmm.enum.UrlType
import com.example.weatherappkmm.network.api_service.Api
import com.example.weatherappkmm.network.api_service.ApiService
import com.example.weatherappkmm.network.response.currency.CurrencyDataResponse
import com.example.weatherappkmm.network.response.news.NewsDataResponse
import com.example.weatherappkmm.network.response.weather.WeatherDataResponse
import io.ktor.http.*

class RepositoryManager(
    private val apiService: ApiService
) {

    suspend fun sendWeatherData(countryName: String): WeatherDataResponse? {
        return apiService.create<WeatherDataResponse>(
            HttpMethod.Get,
            "weather",
            null,
            arrayOf(
                Pair(
                    "q",
                    countryName
                ),
                Pair(
                    "appid",
                    Api.WEATHER_KEY
                )
            ),
            null,
            UrlType.WEATHER_TYPE
        )
    }

    suspend fun sendNewsData(country: String, category: String): NewsDataResponse? {
        return apiService.create<NewsDataResponse>(
            HttpMethod.Get,
            "top-headlines",
            null,
            arrayOf(
                Pair(
                    "country",
                    country
                ),
                Pair(
                    "category",
                    category
                ),
                Pair(
                    "apiKey",
                    Api.NEWS_KEY
                )
            ),
            null,
            UrlType.NEWS_TYPE
        )
    }

    suspend fun sendCurrencyData(from: String, to: String, amount: String): CurrencyDataResponse? {
        return apiService.create<CurrencyDataResponse>(
            HttpMethod.Get,
            "latest",
            null,
            arrayOf(
                Pair(
                    "from",
                    from
                ),
                Pair(
                    "to",
                    to
                ),
                Pair(
                    "amount",
                    amount
                )
            ),
            null,
            UrlType.CURRENCY_TYPE
        )
    }
}