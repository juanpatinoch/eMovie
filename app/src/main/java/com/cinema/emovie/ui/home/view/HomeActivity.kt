package com.cinema.emovie.ui.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cinema.emovie.databinding.ActivityHomeBinding
import com.cinema.emovie.domain.model.Movie
import com.cinema.emovie.ui.home.adapter.HorizontalMovieAdapter
import com.cinema.emovie.ui.home.adapter.VerticalMovieAdapter
import com.cinema.emovie.ui.home.status.HomeStatus
import com.cinema.emovie.ui.home.viewmodel.HomeViewModel
import com.cinema.emovie.ui.movie_detail.view.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private var mediaType = "all"
    private var timeWindow = "day"
    private var upcomingAdapter: HorizontalMovieAdapter? = null
    private var topRatedAdapter: HorizontalMovieAdapter? = null
    private var trendingAdapter: VerticalMovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupAdapters()
        setupRecyclerViews()
        subscribeObserver()
        getInitData()
        setListener()
    }

    private fun setupBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupAdapters() {
        upcomingAdapter = HorizontalMovieAdapter { navigateToMovieDetail(it) }
        topRatedAdapter = HorizontalMovieAdapter { navigateToMovieDetail(it) }
        trendingAdapter = VerticalMovieAdapter { navigateToMovieDetail(it) }
    }

    private fun setupRecyclerViews() = with(binding) {
        recyclerViewUpcoming.apply {
            setHasFixedSize(false)
            isNestedScrollingEnabled = false;
            adapter = upcomingAdapter
        }
        recyclerViewTopRated.apply {
            setHasFixedSize(false)
            isNestedScrollingEnabled = false;
            adapter = topRatedAdapter
        }
        recyclerViewTrending.apply {
            setHasFixedSize(false)
            isNestedScrollingEnabled = false;
            adapter = trendingAdapter
        }
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
        viewModel.getInitData(mediaType, timeWindow)
    }

    private fun getTrendingData() {
        viewModel.getTrendingData(mediaType, timeWindow)
    }

    private fun setListener() = with(binding) {
        radioGroupMediaType.setOnCheckedChangeListener { _, id ->
            onMediaTypeChange(id)
        }
        radioGroupTimeWindow.setOnCheckedChangeListener { _, id ->
            onTimeWindowChange(id)
        }
    }

    private fun onMediaTypeChange(id: Int) = with(binding) {
        when (id) {
            radioButtonMediaTypeAll.id -> {
                mediaType = "all"
                getTrendingData()
            }
            radioButtonMediaTypeMovie.id -> {
                mediaType = "movie"
                getTrendingData()
            }
            radioButtonMediaTypeTvShows.id -> {
                mediaType = "tv"
                getTrendingData()
            }
        }
    }

    private fun onTimeWindowChange(id: Int) = with(binding) {
        when (id) {
            radioButtonTimeWindowDay.id -> {
                timeWindow = "day"
                getTrendingData()
            }
            radioButtonTimeWindowWeek.id -> {
                timeWindow = "week"
                getTrendingData()
            }
        }
    }

    private fun setUpcomingData(movies: List<Movie>?) {
        upcomingAdapter?.submitList(movies)
    }

    private fun setTopRatedData(movies: List<Movie>?) {
        topRatedAdapter?.submitList(movies)
    }

    private fun setTrendingData(movies: List<Movie>?) {
        trendingAdapter?.submitList(movies)
    }

    private fun navigateToMovieDetail(movieItem: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movieItem", movieItem)
        startActivity(intent)
    }

}