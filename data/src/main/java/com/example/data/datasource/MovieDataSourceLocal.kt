package com.example.data.datasource

import com.example.data.entity.MovieDAO
import io.reactivex.Completable
import io.reactivex.Maybe

interface MovieDataSourceLocal {

    fun getFavorites(): Maybe<List<MovieDAO>>

    fun addFavorite(movie: MovieDAO): Completable

    fun deleteFavorite(movie: MovieDAO): Completable
}