package com.example.data.mapper

import com.example.data.entity.MovieDAO
import com.example.data.entity.MovieDTO

class MovieMapperImpl: MovieMapper {

    override fun map(input: MovieDTO): MovieDAO {
        return MovieDAO(
            input.id,
            input.posterPath,
            input.originalTitle,
            input.overview,
            input.originalLanguage
        )
    }

    override fun map(input: MovieDAO): MovieDTO {
        return MovieDTO(
            input.id,
            input.posterPath,
            input.originalTitle,
            input.overview,
            input.originalLanguage
        )
    }
}