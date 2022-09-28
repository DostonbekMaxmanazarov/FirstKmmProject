package com.example.weatherappkmm.mvp.view

import com.example.weatherappkmm.network.response.currency.CurrencyDataResponse

interface ICurrencyView : IBaseView {
    fun sendCurrencyData(currencyDataResponse: CurrencyDataResponse)
}