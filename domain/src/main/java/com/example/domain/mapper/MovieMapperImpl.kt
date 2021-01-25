package com.example.domain.mapper

import com.example.data.entity.MovieDTO
import com.example.domain.entity.MovieBO

class MovieMapperImpl: MovieMapper {

    override fun map(input: MovieDTO): MovieBO {
        return MovieBO(
            input.id,
            input.posterPath,
            input.originalTitle,
            input.overview,
            input.originalLanguage
        )
    }

    override fun map(input: MovieBO): MovieDTO {
        return MovieDTO(
            input.id,
            input.posterPath ?: "",
            input.originalTitle,
            input.overview,
            input.originalLanguage
        )
    }
}