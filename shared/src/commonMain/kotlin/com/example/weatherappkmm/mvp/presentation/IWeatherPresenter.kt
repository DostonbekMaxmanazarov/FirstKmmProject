package com.example.weatherappkmm.mvp.presentation

interface IWeatherPresenter {

    fun loadWeather(countryName: String)

    fun cancel()

}