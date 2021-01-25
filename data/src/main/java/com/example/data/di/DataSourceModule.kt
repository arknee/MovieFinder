package com.example.data.di

import com.example.data.datasource.MovieDataSourceCloud
import com.example.data.datasource.MovieDataSourceCloudImpl
import com.example.data.datasource.MovieDataSourceLocal
import com.example.data.datasource.MovieDataSourceLocalImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule: Module = module {

    single { MovieDataSourceCloudImpl(get()) } bind MovieDataSourceCloud::class

    single { MovieDataSourceLocalImpl(get()) } bind MovieDataSourceLocal::class
}