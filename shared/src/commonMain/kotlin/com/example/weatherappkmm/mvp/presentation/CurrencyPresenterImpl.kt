package com.example.weatherappkmm.mvp.presentation

import com.example.weatherappkmm.dispatchers.ioDispatcher
import com.example.weatherappkmm.dispatchers.uiDispatcher
import com.example.weatherappkmm.mvp.view.ICurrencyView
import com.example.weatherappkmm.mvp.view.INewsView
import com.example.weatherappkmm.mvp.view.IWeatherMainView
import kotlinx.coroutines.withContext

class CurrencyPresenterImpl : ICurrencyPresenter,
    BasePresenter<ICurrencyView>(ioDispatcher) {

    override fun loadCurrency(from: String, to: String, amount: String) {
        if (from.isNotEmpty() && to.isNotEmpty() && amount.isNotEmpty()) {
            runAsync {
                val response = apiManager.sendCurrencyData(from, to, amount)
                withContext(uiDispatcher) {
                    if (response != null)
                        view?.sendCurrencyData(response)
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