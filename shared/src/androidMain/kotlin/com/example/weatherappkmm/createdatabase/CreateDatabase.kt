package com.example.weatherappkmm.createdatabase

import android.content.Context
import com.example.weatherappkmm.repository.CurrencyRepository
import com.example.weatherappkmm.db.CurrencyDb
import com.squareup.sqldelight.android.AndroidSqliteDriver

fun createRepository(context: Context): CurrencyRepository {
    val driver = AndroidSqliteDriver(CurrencyDb.Schema, context, "CurrencyDb.db")
    val database = CurrencyDb(driver)
    return CurrencyRepository(database)
}