package com.example.moviefinder.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.AddFavoriteUseCase
import com.example.domain.usecase.DeleteFavoriteUseCase
import com.example.domain.usecase.GetMovieUseCase
import com.example.moviefinder.BuildConfig
import com.example.moviefinder.entity.ImageSizesEnum
import com.example.moviefinder.entity.MovieVO
import com.example.moviefinder.mapper.MovieMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenterImpl(
    private val getMovieUseCase: GetMovieUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val movieMapper: MovieMapper
) : MainPresenter, ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val movieClickedLiveData = MutableLiveData<MovieVO>()
    private val favoriteChangedLiveData = MutableLiveData<MovieVO>()

    override fun movieClicked(movieId: Int) {
        compositeDisposable.add(
            getMovieUseCase.getMovie(BuildConfig.API_KEY, ImageSizesEnum.W500.ordinal, movieId)
                .subscribeOn(Schedulers.io())
                .map(movieMapper::map)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movie ->
                    movieClickedLiveData.value = movie
                }, {
                    Log.e("movieClicked", it.message ?: "Unknown error")
                })
        )
    }

    override fun addFavorite(movie: MovieVO) {
        compositeDisposable.add(
            addFavoriteUseCase.addFavorite(movieMapper.map(movie))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    favoriteChangedLiveData.value = movie.apply {
                        isFavorite = true
                    }
                }, {
                    Log.e("addFavorite", it.message ?: "Unknown error")
                })
        )
    }

    override fun deleteFavorite(movie: MovieVO) {
        compositeDisposable.add(
            deleteFavoriteUseCase.deleteFavorite(movieMapper.map(movie))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    favoriteChangedLiveData.value = movie.apply {
                        isFavorite = false
                    }
                }, {
                    Log.e("deleteFavorite", it.message ?: "Unknown error")
                })
        )
    }

    override fun onMovieClicked(): LiveData<MovieVO> {
        return movieClickedLiveData
    }

    override fun updatedFavoriteStatus(): LiveData<MovieVO> {
        return favoriteChangedLiveData
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}