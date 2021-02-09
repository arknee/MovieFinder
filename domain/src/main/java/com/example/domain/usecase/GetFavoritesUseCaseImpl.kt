package com.example.domain.usecase

import com.example.data.repository.MovieRepository
import com.example.domain.entity.MovieBO
import com.example.domain.mapper.MovieMapper
import io.reactivex.Single

class GetFavoritesUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
) : GetFavoritesUseCase {

    override fun getFavorites(): Single<List<MovieBO>> {
        return movieRepository.getFavorites().map {
            it.map { movie ->
                movieMapper.map(movie).apply {
                    isFavorite = true
                }
            }
        }
    }
}