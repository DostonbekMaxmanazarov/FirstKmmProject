package com.example.weatherappkmm.mvp.view

import com.example.weatherappkmm.network.response.weather.WeatherDataResponse

interface IWeatherMainView : IBaseView {
    fun sendWeatherData(weatherDataResponse: WeatherDataResponse)
}