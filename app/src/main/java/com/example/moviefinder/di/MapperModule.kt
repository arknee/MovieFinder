package com.example.moviefinder.di

import com.example.moviefinder.mapper.MovieMapper
import com.example.moviefinder.mapper.MovieMapperImpl
import com.example.moviefinder.mapper.MovieResponseMapper
import com.example.moviefinder.mapper.MovieResponseMapperImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val appMapperModule: Module = module {

    single { MovieMapperImpl() } bind MovieMapper::class

    single { MovieResponseMapperImpl(get()) } bind MovieResponseMapper::class
}