package com.gb.stopwatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.stopwatch.model.states.AppState
import kotlinx.coroutines.*



abstract class ViewModelStopwatchBase(
    protected val liveData: MutableLiveData<AppState<Long>> = MutableLiveData(),
) : ViewModel() {

    protected val scope = CoroutineScope(
        Dispatchers.IO
                + CoroutineExceptionHandler { _, throwable ->
                    handleError(throwable)
                })
    protected var jobStart: Job = Job()
    protected var jobStop: Job = Job()
    protected var jobWatch: Job = Job()
    protected val delay: Long = 21L

    open fun getWatch(): LiveData<AppState<Long>> = liveData
    abstract fun handleError(error: Throwable)
    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}