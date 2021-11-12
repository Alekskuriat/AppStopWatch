package com.gb.stopwatch.viewmodel

import com.gb.stopwatch.model.repository.Repo
import com.gb.stopwatch.model.states.AppState
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class ViewModelStopwatch(
    private val repo: Repo
) : ViewModelStopwatchBase() {

    fun start() {
        jobStart = scope.launch {
            while (isActive) {
                delay(delay)
                repo.updateTicker(delay)
            }
        }
    }

    fun stop() {
        jobStart.cancel()
        jobStop.cancel()
        jobStop = scope.launch {
            repo.clearTicker()
        }
    }

    fun pause() {
        jobStart.cancel()
    }

    fun watch() {
        jobWatch.cancel()
        jobWatch = scope.launch {
            repo.getTicker().collect {
                liveData.postValue(AppState.Success(it))
            }
        }
    }

    override fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

}