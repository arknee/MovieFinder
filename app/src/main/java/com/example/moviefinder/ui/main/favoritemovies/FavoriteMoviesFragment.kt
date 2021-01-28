package com.example.moviefinder.ui.main.favoritemovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviefinder.databinding.FragmentFavoriteMoviesBinding
import com.example.moviefinder.di.GlideApp
import com.example.moviefinder.ui.main.MainPresenter
import com.example.moviefinder.ui.main.MainPresenterImpl
import com.example.moviefinder.ui.main.favoritemovies.adapter.FavoritesAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMoviesFragment : Fragment() {

    companion object {
        fun newInstance(): FavoriteMoviesFragment = FavoriteMoviesFragment()
    }

    private var binding: FragmentFavoriteMoviesBinding? = null
    private val presenter: FavoriteMoviesPresenter by viewModel<FavoriteMoviesPresenterImpl>()
    private val mainPresenter: MainPresenter by sharedViewModel<MainPresenterImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteMoviesBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.favoriteMoviesRecyclerview?.run {
            context?.let {
                adapter = FavoritesAdapter(GlideApp.with(it), mainPresenter)
                layoutManager = LinearLayoutManager(it)
            }
        }

        presenter.getFavoriteMovies().observe(viewLifecycleOwner, {
            (binding?.favoriteMoviesRecyclerview?.adapter as? FavoritesAdapter)?.run {
                items = it.toMutableList()
                notifyDataSetChanged()
            }
        })

        mainPresenter.updatedFavoriteStatus().observe(viewLifecycleOwner, { updatedMovie ->
            (binding?.favoriteMoviesRecyclerview?.adapter as? FavoritesAdapter)?.run {
                items.iterator().run {
                    while (hasNext()) {
                        if (next().id == updatedMovie.id) {
                            if (updatedMovie.isFavorite) {
                                next().isFavorite = true
                            } else {
                                remove()
                            }
                            notifyDataSetChanged()
                        }
                    }
                }
            }
        })

        presenter.loadFavoriteMovies()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}