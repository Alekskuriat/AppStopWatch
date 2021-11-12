package com.gb.stopwatch.model.states

sealed class AppState<T> {
    data class Success<T>(val time: T) : AppState<T>()
    data class Error<T>(val error: Throwable) : AppState<T>()
}