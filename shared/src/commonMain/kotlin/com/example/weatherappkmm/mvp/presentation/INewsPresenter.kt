package com.example.weatherappkmm.mvp.presentation

interface INewsPresenter {
    fun loadNews(country: String, category: String)

    fun cancel()
}