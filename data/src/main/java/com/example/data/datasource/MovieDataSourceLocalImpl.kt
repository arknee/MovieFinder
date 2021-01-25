package com.example.data.datasource

import com.example.data.dao.MoviesDao
import com.example.data.entity.MovieDAO
import io.reactivex.Completable
import io.reactivex.Maybe

class MovieDataSourceLocalImpl(
    private val db: MoviesDao
): MovieDataSourceLocal {

    override fun getFavorites(): Maybe<List<MovieDAO>> {
        return db.getAllFavorites()
    }

    override fun addFavorite(movie: MovieDAO): Completable {
        return db.addFavorite(movie)
    }

    override fun deleteFavorite(movie: MovieDAO): Completable {
        return db.deleteFavorite(movie)
    }
}