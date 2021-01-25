package com.example.moviefinder.ui.main.favoritemovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetFavoritesUseCase
import com.example.moviefinder.entity.MovieVO
import com.example.moviefinder.mapper.MovieMapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavoriteMoviesPresenterImpl(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val movieMapper: MovieMapper
) : FavoriteMoviesPresenter, ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val favoritesLiveData = MutableLiveData<List<MovieVO>>()

    override fun loadFavoriteMovies() {
        compositeDisposable.add(getFavoritesUseCase.getFavorites()
            .subscribeOn(Schedulers.io())
            .map { it.map(movieMapper::map) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                favoritesLiveData.value = it
            }, {
                Log.e("loadFavoriteMovies", it.message ?: "Unknown error")
            })
        )
    }

    override fun getFavoriteMovies(): LiveData<List<MovieVO>> {
        return favoritesLiveData
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}