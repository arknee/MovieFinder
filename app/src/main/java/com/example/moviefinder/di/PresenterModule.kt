package com.example.moviefinder.di

import com.example.moviefinder.ui.main.MainPresenter
import com.example.moviefinder.ui.main.MainPresenterImpl
import com.example.moviefinder.ui.main.favoritemovies.FavoriteMoviesPresenter
import com.example.moviefinder.ui.main.favoritemovies.FavoriteMoviesPresenterImpl
import com.example.moviefinder.ui.main.home.HomePresenter
import com.example.moviefinder.ui.main.home.HomePresenterImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val presenterModule: Module = module {

    viewModel { HomePresenterImpl(get(), get()) } bind HomePresenter::class

    viewModel { FavoriteMoviesPresenterImpl(get(), get()) } bind FavoriteMoviesPresenter::class

    viewModel { MainPresenterImpl(get(), get(), get(), get()) } bind MainPresenter::class
}