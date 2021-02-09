package com.example.data.repository

import com.example.data.datasource.MovieDataSourceCloud
import com.example.data.datasource.MovieDataSourceLocal
import com.example.data.entity.ConfigurationDTO
import com.example.data.entity.MovieDTO
import com.example.data.entity.MoviesResponseDTO
import com.example.data.mapper.MovieMapper
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.internal.operators.maybe.MaybeSwitchIfEmptySingle

class MovieRepositoryImpl(
    private val movieDataSourceCloud: MovieDataSourceCloud,
    private val movieDataSourceLocal: MovieDataSourceLocal,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override fun getConfiguration(apiKey: String): Single<ConfigurationDTO> {
        return movieDataSourceCloud.getConfiguration(apiKey)
    }

    override fun getPopularMovies(apiKey: String, page: Int): Single<MoviesResponseDTO> {
        return movieDataSourceCloud.getPopularMovies(apiKey, page)
    }

    override fun getMovie(apiKey: String, movieId: Int): Single<MovieDTO> {
        return movieDataSourceCloud.getMovie(apiKey, movieId)
    }

    override fun getFavorites(): Single<List<MovieDTO>> {
        return MaybeSwitchIfEmptySingle(
            movieDataSourceLocal.getFavorites().map {
                it.map(movieMapper::map)
            }, Single.just(emptyList())
        )
    }

    override fun addFavorite(movie: MovieDTO): Completable {
        return movieDataSourceLocal.addFavorite(movieMapper.map(movie))
    }

    override fun deleteFavorite(movie: MovieDTO): Completable {
        return movieDataSourceLocal.deleteFavorite(movieMapper.map(movie))
    }
}