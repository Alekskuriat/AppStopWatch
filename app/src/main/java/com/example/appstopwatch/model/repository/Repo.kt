package com.gb.stopwatch.model.repository

import kotlinx.coroutines.flow.StateFlow

interface Repo {
    suspend fun updateTicker(delay: Long)
    suspend fun getTicker(): StateFlow<Long>
    suspend fun clearTicker()
}