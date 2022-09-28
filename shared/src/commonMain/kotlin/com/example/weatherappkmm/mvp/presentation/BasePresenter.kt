package com.example.weatherappkmm.mvp.presentation

import com.example.weatherappkmm.dispatchers.uiDispatcher
import com.example.weatherappkmm.mvp.view.IBaseView
import com.example.weatherappkmm.network.api_service.ApiService
import com.example.weatherappkmm.repository.RepositoryManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<T : IBaseView>(
    coroutineContext: CoroutineContext
) {

    private var presenterCoroutineScope = PresenterCoroutineScope(coroutineContext)


    protected open val defaultProgressHandler: (Boolean) -> Unit = {
        view?.showLoading(it)
    }

    protected open val defaultErrorHandler: (Throwable) -> Unit = {
        view?.showError(it)
    }


    protected val apiManager = RepositoryManager(ApiService())


    protected var view: T? = null


    fun attachView(view: T): BasePresenter<T> {
        this.view = view
        onViewAttached(view)
        return this
    }

    protected open fun onViewAttached(view: IBaseView) {}

    fun detachView() {
        view = null
        presenterCoroutineScope.viewDetached()
        onViewDetached()
    }

    protected open fun onViewDetached() {}

    protected fun runAsync(
        progressHandler: (Boolean) -> Unit = defaultProgressHandler,
        errorHandler: (Throwable) -> Unit = defaultErrorHandler,
        action: suspend () -> Unit
    ) {
        progressHandler.invoke(true)
        presenterCoroutineScope.launch {
            try {
                action.invoke()
            } catch (e: Exception) {
                withContext(uiDispatcher) {
                    if (e.message?.contains("Unauthorized") == true)
                    //view?.onNotAuthorised()
                    else
                        errorHandler.invoke(e)
                }
            } finally {
                withContext(uiDispatcher) {
                    progressHandler.invoke(false)
                }
            }
        }
    }
}

class PresenterCoroutineScope(
    context: CoroutineContext
) : CoroutineScope {

    private var onViewDetachJob = Job()
    override val coroutineContext: CoroutineContext = context + onViewDetachJob

    fun viewDetached() {
        onViewDetachJob.cancel()
    }
}