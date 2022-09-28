package com.example.weatherappkmm.mvp.presentation

import com.example.weatherappkmm.dispatchers.ioDispatcher
import com.example.weatherappkmm.dispatchers.uiDispatcher
import com.example.weatherappkmm.mvp.view.IWeatherMainView
import kotlinx.coroutines.withContext

class WeatherMainPresenterImpl : IWeatherPresenter,
    BasePresenter<IWeatherMainView>(ioDispatcher) {

    override fun loadWeather(countryName: String) {
        if (countryName.isNotEmpty()) {
            runAsync {
                val response = apiManager.sendWeatherData(countryName)
                withContext(uiDispatcher) {
                    if (response != null)
                        view?.sendWeatherData(response)
                    else
                        view?.showErrorMessage("The object is null!")
                }
            }
        } else {
            view?.showErrorMessage("Entered the field blank")
        }
    }

    override fun cancel() {
        detachView()
    }
}