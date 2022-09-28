package com.example.weatherappkmm.repository

import com.example.weatherappkmm.db.CurrencyDb
import com.example.weatherappkmm.sqldelight.CurrencyTable
import kotlinx.coroutines.*

class CurrencyRepository(
    private val database: CurrencyDb,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    fun getList(): List<CurrencyTable> = database.currencyTableQueries.selectAll().executeAsList()

    suspend fun add(content: String) = withContext(scope.coroutineContext) {
        database.currencyTableQueries.insertCurrency(content)
    }

    suspend fun remove(currencyTable: CurrencyTable) = withContext(scope.coroutineContext) {
        database.currencyTableQueries.deleteById(currencyTable.id)
    }

    suspend fun update(currencyTable: CurrencyTable) = withContext(scope.coroutineContext) {
        database.currencyTableQueries.updateComplete(currencyTable.result_value, currencyTable.id)
    }
}
