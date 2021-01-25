package com.example.domain.di

import com.example.domain.usecase.*
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val useCaseModule: Module = module {

    single { GetMovieUseCaseImpl(get(), get()) } bind GetMovieUseCase::class

    single { GetPopularMoviesUseCaseImpl(get(), get(), get()) } bind GetPopularMoviesUseCase::class

    single { AddFavoriteUseCaseImpl(get(), get()) } bind AddFavoriteUseCase::class

    single { DeleteFavoriteUseCaseImpl(get(), get()) } bind DeleteFavoriteUseCase::class

    single { GetFavoritesUseCaseImpl(get(), get()) } bind GetFavoritesUseCase::class
}