package com.example.data.di

import androidx.room.Room
import com.example.data.BuildConfig
import com.example.data.dao.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val daoModule: Module = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            BuildConfig.DB_NAME
        ).build()
    }

    single {
        (get() as AppDatabase).moviesDao()
    }
}