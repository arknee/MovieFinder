package com.example.domain.usecase

import com.example.domain.entity.MovieBO
import io.reactivex.Completable

interface DeleteFavoriteUseCase {

    fun deleteFavorite(movie: MovieBO): Completable
}