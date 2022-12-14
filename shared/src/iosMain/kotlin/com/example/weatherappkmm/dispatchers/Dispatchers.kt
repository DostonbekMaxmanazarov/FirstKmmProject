package com.example.weatherappkmm.dispatchers

import kotlinx.coroutines.*
import platform.darwin.DISPATCH_QUEUE_PRIORITY_DEFAULT
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_global_queue
import platform.darwin.dispatch_get_main_queue
import kotlin.native.concurrent.freeze

import kotlin.coroutines.CoroutineContext

actual val ioDispatcher: CoroutineContext
    get() = IODispatcher

actual val uiDispatcher: CoroutineContext
    get() = MainDispatcher

actual fun ktorScope(block: suspend () -> Unit) {
    GlobalScope.launch(Dispatchers.Main) { block() }
}

@ThreadLocal
private object MainDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) {
            try {
                block.run().freeze()
            } catch (err: Throwable) {
                throw err
            }
        }
    }
}

@ThreadLocal
private object IODispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
   /*     dispatch_async(
            dispatch_get_global_queue(
                DISPATCH_QUEUE_PRIORITY_DEFAULT.toLong(),
                0.toULong()
            )
        ) {
            try {
                block.run().freeze()
            } catch (err: Throwable) {
                throw err
            }
        }*/
        dispatch_async(dispatch_get_main_queue()) {
            try {
                block.run().freeze()
            } catch (err: Throwable) {
                throw err
            }
        }
    }
}