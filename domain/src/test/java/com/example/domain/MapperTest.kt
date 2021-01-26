package com.example.domain

import com.example.data.entity.MovieDTO
import com.example.domain.di.domainMapperModule
import com.example.domain.entity.MovieBO
import com.example.domain.mapper.MovieMapper
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class MapperTest: AutoCloseKoinTest() {

    private val movieMapper: MovieMapper by inject()

    @Before
    fun before() {
        startKoin {
            modules(domainMapperModule)
        }
    }

    @Test
    fun mapDtoToBo() {
        val input = MovieDTO(
            123,
            "poster_path",
            "original_title",
            "overview",
            "original_language"
        )

        val output = movieMapper.map(input)

        assert(input.id == output.id)
        assert(input.posterPath == output.posterPath)
        assert(input.originalTitle == output.originalTitle)
        assert(input.overview == output.overview)
        assert(input.originalLanguage == output.originalLanguage)
    }

    @Test
    fun mapBoToDto() {
        val input = MovieBO(
            123,
            "poster_path",
            "original_title",
            "overview",
            "original_language"
        )

        val output = movieMapper.map(input)

        assert(input.id == output.id)
        assert(input.posterPath == output.posterPath)
        assert(input.originalTitle == output.originalTitle)
        assert(input.overview == output.overview)
        assert(input.originalLanguage == output.originalLanguage)
    }
}