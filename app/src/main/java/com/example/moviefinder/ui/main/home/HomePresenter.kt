package com.example.moviefinder.ui.main.home

import androidx.lifecycle.LiveData
import com.example.moviefinder.entity.MovieVO
import com.example.moviefinder.entity.MoviesResponseVO

interface HomePresenter {

    fun loadPopularMovies()

    fun getPopularMovies(): LiveData<List<MovieVO>>
}