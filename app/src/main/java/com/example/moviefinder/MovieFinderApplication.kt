package com.example.moviefinder

import android.app.Application
import com.example.data.di.*
import com.example.domain.di.domainMapperModule
import com.example.domain.di.useCaseModule
import com.example.moviefinder.di.appMapperModule
import com.example.moviefinder.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieFinderApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieFinderApplication)
            modules(
                //App Module
                appMapperModule,
                presenterModule,
                //Domain Module
                domainMapperModule,
                useCaseModule,
                //Data Module
                dataMapperModule,
                repositoryModule,
                dataSourceModule,
                apiModule,
                daoModule
            )
        }
    }
}