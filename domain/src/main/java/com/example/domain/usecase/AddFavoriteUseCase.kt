package com.example.domain.usecase

import com.example.domain.entity.MovieBO
import io.reactivex.Completable

interface AddFavoriteUseCase {

    fun addFavorite(movie: MovieBO): Completable
}