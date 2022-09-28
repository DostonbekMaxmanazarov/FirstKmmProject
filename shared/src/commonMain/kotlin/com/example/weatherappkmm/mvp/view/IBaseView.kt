package com.example.weatherappkmm.mvp.view

interface IBaseView {
    fun showLoading(show: Boolean)
    fun showError(error: Throwable)
    fun showErrorMessage(message: String)
}