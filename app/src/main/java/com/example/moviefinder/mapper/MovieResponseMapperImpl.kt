package com.example.moviefinder.mapper

import com.example.domain.entity.MoviesResponseBO
import com.example.moviefinder.entity.MoviesResponseVO

class MovieResponseMapperImpl(
    private val movieMapper: MovieMapper
) : MovieResponseMapper {

    override fun map(input: MoviesResponseBO): MoviesResponseVO {
        return MoviesResponseVO(
            input.page,
            input.totalPages,
            input.totalResults,
            input.results.map(movieMapper::map)
        )
    }
}