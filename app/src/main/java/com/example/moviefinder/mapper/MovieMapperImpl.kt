package com.example.moviefinder.mapper

import com.example.domain.entity.MovieBO
import com.example.moviefinder.entity.MovieVO

class MovieMapperImpl : MovieMapper {

    override fun map(input: MovieBO): MovieVO {
        return MovieVO(
            input.id,
            input.posterPath ?: "",
            input.originalTitle,
            input.overview,
            input.originalLanguage,
            input.isFavorite
        )
    }

    override fun map(input: MovieVO): MovieBO {
        return MovieBO(
            input.id,
            input.posterPath,
            input.originalTitle,
            input.overview,
            input.originalLanguage,
            input.isFavorite
        )
    }
}