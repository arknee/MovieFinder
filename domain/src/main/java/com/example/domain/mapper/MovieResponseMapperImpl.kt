package com.example.domain.mapper

import com.example.data.entity.MoviesResponseDTO
import com.example.domain.entity.MoviesResponseBO

class MovieResponseMapperImpl(
    private val movieMapper: MovieMapper
): MovieResponseMapper {

    override fun map(input: MoviesResponseDTO): MoviesResponseBO {
        return MoviesResponseBO(
            input.page,
            input.totalPages,
            input.totalResults,
            input.results.map(movieMapper::map)
        )
    }
}