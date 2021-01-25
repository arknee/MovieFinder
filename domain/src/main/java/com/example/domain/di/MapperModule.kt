package com.example.domain.di

import com.example.domain.mapper.MovieMapper
import com.example.domain.mapper.MovieMapperImpl
import com.example.domain.mapper.MovieResponseMapper
import com.example.domain.mapper.MovieResponseMapperImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val domainMapperModule: Module = module {

    single { MovieMapperImpl() } bind MovieMapper::class

    single { MovieResponseMapperImpl(get()) } bind MovieResponseMapper::class
}