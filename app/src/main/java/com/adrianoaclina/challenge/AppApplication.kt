package com.adrianoaclina.challenge

import android.app.Application
import com.adrianoaclina.challenge.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    databaseModule,
                    daoModule,
                    uiModule,
                    viewModelModule
                )
            )
        }
    }
}