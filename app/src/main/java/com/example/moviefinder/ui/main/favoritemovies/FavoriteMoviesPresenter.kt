package com.example.moviefinder.ui.main.favoritemovies

import androidx.lifecycle.LiveData
import com.example.moviefinder.entity.MovieVO

interface FavoriteMoviesPresenter {

    fun loadFavoriteMovies()

    fun getFavoriteMovies(): LiveData<List<MovieVO>>
}