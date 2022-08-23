package com.cinema.emovie.ui.home.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cinema.emovie.databinding.ActivityHomeBinding
import com.cinema.emovie.domain.model.Movie
import com.cinema.emovie.ui.home.adapter.HorizontalMovieAdapter
import com.cinema.emovie.ui.home.adapter.VerticalMovieAdapter
import com.cinema.emovie.ui.home.status.HomeStatus
import com.cinema.emovie.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var mediaType: String? = null
    private var timeWindow: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        subscribeObserver()
        getInitData()
    }

    private fun setupBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun subscribeObserver() {
        viewModel.homeStatus.observe(this) {
            when (it) {
                is HomeStatus.SuccessGetUpcoming -> setUpcomingData(it.movies)
                is HomeStatus.SuccessGetTopRated -> setTopRatedData(it.movies)
                is HomeStatus.SuccessGetTrending -> setTrendingData(it.movies)
                is HomeStatus.Error -> {
                    //TODO: Pendiente hacer implementacion de errorView
                }
            }
        }
    }

    private fun getInitData() {
        viewModel.getInitData("all", "week")
    }

    private fun setUpcomingData(movies: List<Movie>?) = with(binding) {
        val horizontalMovieAdapter = HorizontalMovieAdapter({})
        recyclerViewUpcoming.apply {
            setHasFixedSize(false)
            isNestedScrollingEnabled = false;
            adapter = horizontalMovieAdapter
        }
        horizontalMovieAdapter.submitList(movies)
    }

    private fun setTopRatedData(movies: List<Movie>?) = with(binding) {
        val horizontalMovieAdapter = HorizontalMovieAdapter({})
        recyclerViewTopRated.apply {
            setHasFixedSize(false)
            isNestedScrollingEnabled = false;
            adapter = horizontalMovieAdapter
        }
        horizontalMovieAdapter.submitList(movies)
    }

    private fun setTrendingData(movies: List<Movie>?) = with(binding) {
        val verticalMovieAdapter = VerticalMovieAdapter({})
        recyclerViewTrending.apply {
            setHasFixedSize(false)
            isNestedScrollingEnabled = false
            adapter = verticalMovieAdapter
        }
        verticalMovieAdapter.submitList(movies)
    }
}