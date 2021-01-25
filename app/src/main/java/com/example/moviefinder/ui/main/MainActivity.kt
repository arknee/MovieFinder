package com.example.moviefinder.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.moviefinder.R
import com.example.moviefinder.databinding.ActivityMainBinding
import com.example.moviefinder.di.GlideApp
import com.example.moviefinder.ui.common.MovieViewerDialog
import com.example.moviefinder.ui.main.favoritemovies.FavoriteMoviesFragment
import com.example.moviefinder.ui.main.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val presenter: MainPresenter by viewModel<MainPresenterImpl>()
    private var movieDialog: MovieViewerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> replaceFragment(HomeFragment.newInstance())
                    R.id.navigation_favorite_movies -> replaceFragment(FavoriteMoviesFragment.newInstance())
                }
                true
            }
            selectedItemId = R.id.navigation_home
        }

        presenter.onMovieClicked().observe(this, {
            movieDialog = MovieViewerDialog(this, GlideApp.with(this), it)
            movieDialog?.show()
        })
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().run {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onStop() {
        movieDialog?.dismiss()
        super.onStop()
    }

    override fun onBackPressed() {
        if (binding.bottomNavigationView.selectedItemId != R.id.navigation_home) {
            binding.bottomNavigationView.selectedItemId = R.id.navigation_home
        } else {
            finish()
        }
    }
}