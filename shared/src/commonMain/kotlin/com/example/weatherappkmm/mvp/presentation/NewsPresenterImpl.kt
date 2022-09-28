package com.example.weatherappkmm.mvp.presentation

import com.example.weatherappkmm.dispatchers.ioDispatcher
import com.example.weatherappkmm.dispatchers.uiDispatcher
import com.example.weatherappkmm.mvp.view.INewsView
import kotlinx.coroutines.withContext

class NewsPresenterImpl : INewsPresenter,
    BasePresenter<INewsView>(ioDispatcher) {

    override fun loadNews(country: String, category: String) {
        if (country.isNotEmpty() && category.isNotEmpty()) {
            runAsync {
                val response = apiManager.sendNewsData(country, category)
                withContext(uiDispatcher) {
                    if (response != null)
                        view?.sendNewsData(response)
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