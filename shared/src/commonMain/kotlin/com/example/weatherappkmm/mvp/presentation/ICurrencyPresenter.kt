package com.example.weatherappkmm.mvp.presentation

interface ICurrencyPresenter {
    fun loadCurrency(from: String,to:String,amount:String)

    fun cancel()
}