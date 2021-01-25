package com.example.data.di

import com.example.data.repository.MovieRepository
import com.example.data.repository.MovieRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule: Module = module {

    single { MovieRepositoryImpl(get(), get(), get()) } bind MovieRepository::class
}