package com.example.weatherappkmm

import com.example.weatherappkmm.db.CurrencyDb
import com.example.weatherappkmm.sqldelight.CurrencyTable
import kotlinx.coroutines.*
import kotlin.native.concurrent.freeze

/*
class CurrencyRepoIos(database: CurrencyDb,
                      dispatcher: CoroutineDispatcher = Dispatchers.Default) {
    private val repository = CurrencyRepoIos(database, dispatcher)
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    init {
        freeze()
    }

    fun getList() = repository.getList()
    fun add(content: String) = scope.launch { repository.add(content) }
    fun remove(currencyTable: CurrencyTable) = scope.launch { repository.remove(currencyTable) }
    fun toggleComplete(currencyTable: CurrencyTable) = scope.launch { repository.toggleComplete(toDo) }
}

// This lets us do Flow<ToDos> instead of Flow<List<ToDo>> for better iOS generics experience
data class CurrencyTable(val items: List<CurrencyTable>) {
    init {
        freeze()
    }
}*/
