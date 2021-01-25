package com.example.moviefinder.mapper

import com.example.domain.entity.MoviesResponseBO
import com.example.moviefinder.entity.MoviesResponseVO

interface MovieResponseMapper {

    fun map(input: MoviesResponseBO): MoviesResponseVO
}