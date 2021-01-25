package com.example.data.dao

import androidx.room.*
import com.example.data.entity.MovieDAO
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MovieDAO")
    fun getAllFavorites(): Maybe<List<MovieDAO>>

    @Insert
    fun addFavorite(movie: MovieDAO): Completable

    @Delete
    fun deleteFavorite(movie: MovieDAO): Completable
}