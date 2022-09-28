package com.example.weatherappkmm.mvp.view

import com.example.weatherappkmm.network.response.news.NewsDataResponse

interface INewsView:IBaseView {
    fun sendNewsData(newsDataResponse: NewsDataResponse)
}