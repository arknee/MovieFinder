package com.example.moviefinder.mapper

import com.example.domain.entity.MovieBO
import com.example.moviefinder.entity.MovieVO

interface MovieMapper {

    fun map(input: MovieBO): MovieVO

    fun map(input: MovieVO): MovieBO
}