package com.example.domain.mapper

import com.example.data.entity.MoviesResponseDTO
import com.example.domain.entity.MoviesResponseBO

interface MovieResponseMapper {

    fun map(input: MoviesResponseDTO): MoviesResponseBO
}