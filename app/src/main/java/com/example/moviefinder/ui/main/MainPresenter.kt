package com.example.moviefinder.ui.main

import androidx.lifecycle.LiveData
import com.example.moviefinder.entity.MovieVO

interface MainPresenter {

    fun movieClicked(movieId: Int)

    fun addFavorite(movie: MovieVO)

    fun deleteFavorite(movie: MovieVO)

    fun onMovieClicked(): LiveData<MovieVO>

    fun updatedFavoriteStatus(): LiveData<MovieVO>
}