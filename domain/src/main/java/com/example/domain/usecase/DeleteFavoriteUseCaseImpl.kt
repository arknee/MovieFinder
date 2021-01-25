package com.example.domain.usecase

import com.example.data.repository.MovieRepository
import com.example.domain.entity.MovieBO
import com.example.domain.mapper.MovieMapper
import io.reactivex.Completable

class DeleteFavoriteUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
): DeleteFavoriteUseCase {

    override fun deleteFavorite(movie: MovieBO): Completable {
        return movieRepository.deleteFavorite(movieMapper.map(movie))
    }
}