package com.example.moviefinder.ui.main.favoritemovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.databinding.ItemMovieBinding
import com.example.moviefinder.di.GlideRequests
import com.example.moviefinder.entity.MovieVO
import com.example.moviefinder.ui.common.MovieViewHolder
import com.example.moviefinder.ui.main.MainPresenter

class FavoritesAdapter(
    private val glide: GlideRequests,
    private val mainPresenter: MainPresenter,
    var items: MutableList<MovieVO> = mutableListOf()
): RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            glide,
            mainPresenter::movieClicked,
            mainPresenter::addFavorite,
            mainPresenter::deleteFavorite
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}