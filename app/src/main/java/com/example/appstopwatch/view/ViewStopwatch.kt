package com.gb.stopwatch.view

import android.widget.TextView
import com.gb.stopwatch.model.states.AppState

interface ViewStopwatch {
    fun renderData(appState: AppState<Long>, textView: TextView)
    fun initButtons()
}