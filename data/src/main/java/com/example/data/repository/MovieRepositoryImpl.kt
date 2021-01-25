package com.example.data.repository

import com.example.data.datasource.MovieDataSourceCloud
import com.example.data.datasource.MovieDataSourceLocal
import com.example.data.entity.MovieDAO
import com.example.data.entity.MovieDTO
import com.example.data.entity.MoviesResponseDTO
import com.example.data.mapper.MovieMapper
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class MovieRepositoryImpl(
    private val movieDataSourceCloud: MovieDataSourceCloud,
    private val movieDataSourceLocal: MovieDataSourceLocal,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override fun getPopularMovies(apiKey: String, page: Int): Single<MoviesResponseDTO> {
        return movieDataSourceCloud.getPopularMovies(apiKey, page)
    }

    override fun getMovie(apiKey: String, movieId: Int): Single<MovieDTO> {
        return movieDataSourceCloud.getMovie(apiKey, movieId)
    }

    override fun getFavorites(): Maybe<List<MovieDTO>> {
        return movieDataSourceLocal.getFavorites().map {
            it.map(movieMapper::map)
        }
    }

    override fun addFavorite(movie: MovieDTO): Completable {
        return movieDataSourceLocal.addFavorite(movieMapper.map(movie))
    }

    override fun deleteFavorite(movie: MovieDTO): Completable {
        return movieDataSourceLocal.deleteFavorite(movieMapper.map(movie))
    }
}