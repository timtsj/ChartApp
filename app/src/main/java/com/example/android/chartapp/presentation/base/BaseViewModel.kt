package com.example.android.chartapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @author Timur Seisembayev
 * @since 29.02.2020
 */

abstract class BaseViewModel : ViewModel() {

    private var isActive = true

    // Do work in IO
    fun <P> doWork(doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        doCoroutineWork(doOnAsyncBlock, viewModelScope, IO)
    }

    // Do work in Main
    // doWorkInMainThread {...}
    fun <P> doWorkInMainThread(doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        doCoroutineWork(doOnAsyncBlock, viewModelScope, Main)
    }

    // Do work in IO repeately
    // doRepeatWork(1000) {...}
    // then we need to stop it calling stopRepeatWork()
    fun <P> doRepeatWork(delay: Long, doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        isActive = true
        viewModelScope.launch {
            while (this@BaseViewModel.isActive) {
                withContext(IO) {
                    doOnAsyncBlock.invoke(this)
                }
                if (this@BaseViewModel.isActive) {
                    kotlinx.coroutines.delay(delay)
                }
            }
        }
    }

    fun stopRepeatWork() {
        isActive = false
    }

    override fun onCleared() {
        super.onCleared()
        isActive = false
    }

    private inline fun <P> doCoroutineWork(
            crossinline doOnAsyncBlock: suspend CoroutineScope.() -> P,
            coroutineScope: CoroutineScope,
            context: CoroutineContext
    ) {
        coroutineScope.launch {
            withContext(context) {
                doOnAsyncBlock.invoke(this)
            }
        }
    }
}