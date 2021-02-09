package com.example.moviefinder.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetPopularMoviesUseCase
import com.example.moviefinder.BuildConfig
import com.example.moviefinder.entity.ImageSizesEnum
import com.example.moviefinder.entity.MovieVO
import com.example.moviefinder.entity.MoviesPaginationVO
import com.example.moviefinder.mapper.MovieResponseMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenterImpl(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val movieResponseMapper: MovieResponseMapper
) : HomePresenter, ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val moviesLiveData = MutableLiveData<List<MovieVO>>()
    private var moviesPagination: MoviesPaginationVO? = null

    override fun loadPopularMovies() {
        compositeDisposable.add(
            getPopularMoviesUseCase.getPopularMovies(
                BuildConfig.API_KEY,
                ImageSizesEnum.W185.ordinal,
                moviesPagination?.page ?: 1
            )
                .subscribeOn(Schedulers.io())
                .map(movieResponseMapper::map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    moviesLiveData.value = response.results
                    moviesPagination = MoviesPaginationVO(
                        response.page + 1,
                        response.totalPages,
                        response.totalResults
                    )
                }, {
                    Log.e("loadPopularMovies", it.message ?: "Unknown error")
                })
        )
    }

    override fun getPopularMovies(): LiveData<List<MovieVO>> {
        return moviesLiveData
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}