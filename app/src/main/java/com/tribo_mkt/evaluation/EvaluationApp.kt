package com.tribo_mkt.evaluation

import android.app.Application
import com.tribo_mkt.evaluation.koin.repositoryModule
import com.tribo_mkt.evaluation.koin.retrofitModule
import com.tribo_mkt.evaluation.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EvaluationApp : Application() {

    private val appModules = listOf(
        repositoryModule,
        retrofitModule,
        viewModelModule
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