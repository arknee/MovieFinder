package com.example.moviefinder.ui.common

import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.R
import com.example.moviefinder.databinding.ItemMovieBinding
import com.example.moviefinder.di.GlideRequests
import com.example.moviefinder.entity.MovieVO

class MovieViewHolder(
    private val binding: ItemMovieBinding,
    private val glide: GlideRequests,
    private val movieClicked: (Int) -> Unit,
    private val addFavorite: (MovieVO) -> Unit,
    private val deleteFavorite: (MovieVO) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieVO) {
        binding.run {
            glide.load(item.posterUrl)
                .placeholder(R.drawable.ic_baseline_local_movies_24)
                .error(R.drawable.ic_baseline_local_movies_24)
                .fitCenter()
                .into(moviePoster)
            movieTitle.text = item.originalTitle
            favoriteSwitch.setOnCheckedChangeListener(null)
            favoriteSwitch.isChecked = item.isFavorite
            favoriteSwitch.setOnCheckedChangeListener { _, _ ->
                if (item.isFavorite) {
                    deleteFavorite(item)
                } else {
                    addFavorite(item)
                }
            }
            root.setOnClickListener {
                movieClicked(item.id)
            }
        }
    }
}