package com.example.moviefinder.ui.common

import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.databinding.ItemLoadingBinding

class LoadingViewHolder(
    private val binding: ItemLoadingBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.loadingProgress
    }
}