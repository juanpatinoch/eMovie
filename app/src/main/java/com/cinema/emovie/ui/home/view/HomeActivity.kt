package com.cinema.emovie.ui.home.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cinema.emovie.common.adapter.MovieAdapter
import com.cinema.emovie.databinding.ActivityHomeBinding
import com.cinema.emovie.domain.model.Movie
import com.cinema.emovie.ui.home.status.HomeStatus
import com.cinema.emovie.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: HomeViewModel by viewModels()

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
                is HomeStatus.Error -> {
                    //TODO: Pendiente hacer implementacion de errorView
                }
            }
        }
    }

    private fun getInitData() {
        viewModel.getInitData()
    }

    private fun setUpcomingData(movies: List<Movie>?) = with(binding) {

        val myAdapter = MovieAdapter({})
        recyclerViewUpcoming.apply {
            adapter = myAdapter
        }
        myAdapter.submitList(movies)
    }
}