package com.example.moviefinder.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.databinding.FragmentHomeBinding
import com.example.moviefinder.di.GlideApp
import com.example.moviefinder.ui.main.MainPresenter
import com.example.moviefinder.ui.main.MainPresenterImpl
import com.example.moviefinder.ui.main.home.adapter.MoviesAdapter
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private var binding: FragmentHomeBinding? = null
    private val presenter: HomePresenter by viewModel<HomePresenterImpl>()
    private val mainPresenter: MainPresenter by sharedViewModel<MainPresenterImpl>()
    private var isLoading = false
    private val recyclerViewScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            (recyclerView.layoutManager as? LinearLayoutManager)?.let { manager ->
                if (!isLoading) {
                    if (manager.findLastVisibleItemPosition() == manager.itemCount - 1) {
                        isLoading = true
                        presenter.loadPopularMovies()
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.homeRecyclerview?.run {
            context?.let {
                adapter = MoviesAdapter(GlideApp.with(it), mainPresenter)
                layoutManager = LinearLayoutManager(it)
            }
        }

        presenter.getPopularMovies().observe(viewLifecycleOwner, {
            (binding?.homeRecyclerview?.adapter as? MoviesAdapter)?.run {
                if (!items.containsAll(it)) {
                    items.addAll(it)
                    notifyDataSetChanged()
                }
            }
            isLoading = false
        })

        mainPresenter.updatedFavoriteStatus().observe(viewLifecycleOwner, { updatedMovie ->
            (binding?.homeRecyclerview?.adapter as? MoviesAdapter)?.run {
                items.map {
                    if (it.id == updatedMovie.id) {
                        it.isFavorite = updatedMovie.isFavorite
                        notifyDataSetChanged()
                    }
                }
            }
        })

        isLoading = true
        presenter.loadPopularMovies()
    }

    override fun onResume() {
        super.onResume()
        binding?.homeRecyclerview?.addOnScrollListener(recyclerViewScrollListener)
    }

    override fun onPause() {
        binding?.homeRecyclerview?.removeOnScrollListener(recyclerViewScrollListener)
        super.onPause()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}