package com.example.weatherappkmm.android

import android.app.Application
import com.example.weatherappkmm.repository.CurrencyRepository
import com.example.weatherappkmm.createdatabase.createRepository

class App : Application() {

    companion object {
        var database: CurrencyRepository? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = createRepository(this)
    }
}