package com.gb.stopwatch.model.di

import com.gb.stopwatch.model.repository.Repo
import com.gb.stopwatch.model.repository.RepoImpl
import com.gb.stopwatch.viewmodel.ViewModelStopwatch
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

class Koin {
    fun getModule() = module {
        factory<Repo> { RepoImpl() }

        viewModel { ViewModelStopwatch(repo = get()) }

    }
}