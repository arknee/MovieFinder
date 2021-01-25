package com.example.domain.mapper

import com.example.data.entity.MovieDTO
import com.example.domain.entity.MovieBO

interface MovieMapper {

    fun map(input: MovieDTO): MovieBO

    fun map(input: MovieBO): MovieDTO
}