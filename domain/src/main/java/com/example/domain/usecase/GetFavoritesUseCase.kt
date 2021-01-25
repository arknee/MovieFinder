package com.example.domain.usecase

import com.example.domain.entity.MovieBO
import io.reactivex.Maybe
import io.reactivex.Single

interface GetFavoritesUseCase {

    fun getFavorites(): Single<List<MovieBO>>
}