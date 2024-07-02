package com.example.usersgithub

import android.app.Application
import com.example.usersgithub.di.databaseModule
import com.example.usersgithub.di.networkModule
import com.example.usersgithub.di.repositoryModule
import com.example.usersgithub.di.useCaseModule
import com.example.usersgithub.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}