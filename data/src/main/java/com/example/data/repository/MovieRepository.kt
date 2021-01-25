package com.example.data.repository

import com.example.data.entity.MovieDTO
import com.example.data.entity.MoviesResponseDTO
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface MovieRepository {

    fun getPopularMovies(apiKey: String, page: Int): Single<MoviesResponseDTO>

    fun getMovie(apiKey: String, movieId: Int): Single<MovieDTO>

    fun getFavorites(): Maybe<List<MovieDTO>>

    fun addFavorite(movie: MovieDTO): Completable

    fun deleteFavorite(movie: MovieDTO): Completable
}