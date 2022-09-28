package com.example.weatherappkmm.dispatchers

import kotlin.coroutines.CoroutineContext

expect val ioDispatcher: CoroutineContext

expect val uiDispatcher: CoroutineContext

expect fun ktorScope(block: suspend () -> Unit)