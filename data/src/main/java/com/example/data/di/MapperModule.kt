package com.example.data.di

import com.example.data.mapper.MovieMapper
import com.example.data.mapper.MovieMapperImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val dataMapperModule: Module = module {

    single { MovieMapperImpl() } bind MovieMapper::class
}