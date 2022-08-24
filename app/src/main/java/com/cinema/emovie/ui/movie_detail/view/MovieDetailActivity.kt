package com.cinema.emovie.ui.movie_detail.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cinema.emovie.common.getFromLocalStorage
import com.cinema.emovie.common.loadFromBitmap
import com.cinema.emovie.databinding.ActivityMovieDetailBinding
import com.cinema.emovie.domain.model.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private var movieItem: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupAppBar()
        getInfoArguments()
        showMovieData()
    }

    private fun setupBinding() {
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupAppBar() = with(binding) {
        layoutAppbar.imageViewAppbarLogo.visibility = View.GONE
        layoutAppbar.imageViewAppbarBack.visibility = View.VISIBLE
    }

    private fun getInfoArguments() {
        movieItem = intent.getSerializableExtra("movieItem") as Movie
    }

    private fun showMovieData() {
        movieItem?.let {
            getPosterImage(it.posterUrl)
        }
    }

    private fun getPosterImage(url: String?) {
        val localStorageBitmap = url?.getFromLocalStorage()
        localStorageBitmap?.let {
            binding.imageViewMovieDetailPoster.loadFromBitmap(this, it)
        }
    }

}
