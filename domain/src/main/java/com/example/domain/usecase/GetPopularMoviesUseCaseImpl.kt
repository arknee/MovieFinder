package com.example.domain.usecase

import com.example.data.repository.MovieRepository
import com.example.domain.entity.MoviesResponseBO
import com.example.domain.mapper.MovieMapper
import com.example.domain.mapper.MovieResponseMapper
import io.reactivex.Single

class GetPopularMoviesUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val movieResponseMapper: MovieResponseMapper,
    private val movieMapper: MovieMapper,
    private val getBaseUrlUseCase: GetBaseUrlUseCase
) : GetPopularMoviesUseCase {

    override fun getPopularMovies(
        apiKey: String,
        imageSize: Int,
        page: Int
    ): Single<MoviesResponseBO> {
        return movieRepository.getPopularMovies(apiKey, page)
            .map(movieResponseMapper::map)
            .flatMap { moviesResponse ->
                getBaseUrlUseCase.getBaseUrl(apiKey).flatMap { imageUrl ->
                    movieRepository.getFavorites().flatMap { favorites ->
                        moviesResponse.results.map { movie ->
                            movie.posterPath =
                                "${imageUrl.secureBaseUrl}${imageUrl.posterSizes[imageSize]}${movie.posterPath}"
                            favorites.find {
                                movie.id == it.id
                            }?.let {
                                movie.isFavorite = true
                            }
                        }
                        Single.just(moviesResponse)
                    }
                }
            }
    }
}