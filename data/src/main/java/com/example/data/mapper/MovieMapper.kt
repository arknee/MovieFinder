package com.example.data.mapper

import com.example.data.entity.MovieDAO
import com.example.data.entity.MovieDTO

interface MovieMapper {

    fun map(input: MovieDTO): MovieDAO

    fun map(input: MovieDAO): MovieDTO
}