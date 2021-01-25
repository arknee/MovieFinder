package com.example.domain.usecase

import com.example.data.repository.MovieRepository
import com.example.domain.entity.MovieBO
import com.example.domain.mapper.MovieMapper
import io.reactivex.Completable

class AddFavoriteUseCaseImpl(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
): AddFavoriteUseCase {

    override fun addFavorite(movie: MovieBO): Completable {
        return movieRepository.addFavorite(movieMapper.map(movie))
    }
}