package com.gb.stopwatch.model.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class RepoImpl : Repo {

    private val ticker = MutableStateFlow(0L)

    override suspend fun updateTicker(delay: Long) {
        ticker.value += delay
    }

    override suspend fun getTicker(): StateFlow<Long> = ticker

    override suspend fun clearTicker() {
        ticker.value = 0L
    }



}