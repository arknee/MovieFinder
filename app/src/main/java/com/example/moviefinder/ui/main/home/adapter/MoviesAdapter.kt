package com.example.moviefinder.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.databinding.ItemMovieBinding
import com.example.moviefinder.di.GlideRequests
import com.example.moviefinder.entity.MovieVO
import com.example.moviefinder.ui.common.MovieViewHolder
import com.example.moviefinder.ui.main.MainPresenter

class MoviesAdapter(
    private val glide: GlideRequests,
    private val mainPresenter: MainPresenter,
    var items: MutableList<MovieVO> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            glide,
            mainPresenter::movieClicked,
            mainPresenter::addFavorite,
            mainPresenter::deleteFavorite
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MovieViewHolder)?.bind(items[holder.layoutPosition])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}