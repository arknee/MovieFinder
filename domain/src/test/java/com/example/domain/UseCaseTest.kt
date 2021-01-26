package com.example.domain

import com.example.data.entity.MovieDTO
import com.example.data.repository.MovieRepository
import com.example.domain.mapper.MovieMapper
import com.example.domain.usecase.GetMovieUseCase
import com.example.domain.usecase.GetMovieUseCaseImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class UseCaseTest {

    companion object {
        private const val MOVIE_ID = 123456
        private const val API_KEY = "api_key"
    }

    private val movie = MovieDTO(
        MOVIE_ID,
        "poster_path",
        "original_title",
        "overview",
        "original_language"
    )

    private lateinit var getMovieUseCase: GetMovieUseCase

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var movieMapper: MovieMapper

    @Before
    fun before() {
        MockitoAnnotations.openMocks(this)
        getMovieUseCase = GetMovieUseCaseImpl(movieRepository, movieMapper)
    }

    @Test
    fun testGetMovieUseCase() {
        //WHEN
        `when`(movieRepository.getMovie(API_KEY, MOVIE_ID)).thenReturn(Single.just(movie))

        //THEN
        getMovieUseCase.getMovie(API_KEY, MOVIE_ID)

        //WHAT
        verify(movieRepository).getMovie(API_KEY, MOVIE_ID)
    }
}