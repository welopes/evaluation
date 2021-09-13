package com.welopes.evaluation

import android.app.Application
import com.welopes.evaluation.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EvaluationApp : Application() {

    private val appModules = listOf(
        repositoryModule,
        retrofitModule,
        userViewModelModule,
        albumsViewModelModule,
        postsViewModelModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@EvaluationApp)
            modules(appModules)
        }
    }
}