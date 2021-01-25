package com.example.moviefinder.ui.common

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.example.moviefinder.BuildConfig
import com.example.moviefinder.R
import com.example.moviefinder.databinding.DialogMovieViewerBinding
import com.example.moviefinder.di.GlideRequests
import com.example.moviefinder.entity.MovieVO

class MovieViewerDialog(
    context: Context,
    private val glide: GlideRequests,
    private val movieVO: MovieVO
) : AlertDialog(context) {

    private var binding: DialogMovieViewerBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogMovieViewerBinding.inflate(
            layoutInflater,
            window?.decorView?.rootView as ViewGroup,
            false
        )
        setView(binding?.root)
        setCancelable(false)
        super.onCreate(savedInstanceState)
        binding?.run {
            "${context.getString(R.string.id)} ${movieVO.id}".also {
                movieIdText.text = it
            }
            movieTitleText.text = movieVO.originalTitle
            glide.load("${BuildConfig.IMAGE_BASE_URL}${movieVO.posterPath}")
                .placeholder(R.drawable.ic_baseline_local_movies_24)
                .error(R.drawable.ic_baseline_local_movies_24)
                .fitCenter()
                .into(moviePosterImage)
            "${context.getString(R.string.language)} ${movieVO.originalLanguage}".also {
                movieLanguageText.text = it
            }
            movieOverviewText.text = movieVO.overview
            "${context.getString(R.string.saved_favorite)} ${movieVO.isFavorite}".also {
                movieIsFavoriteText.text = it
            }
            closeButton.setOnClickListener { dismiss() }
        }
    }

    override fun dismiss() {
        binding = null
        super.dismiss()
    }
}