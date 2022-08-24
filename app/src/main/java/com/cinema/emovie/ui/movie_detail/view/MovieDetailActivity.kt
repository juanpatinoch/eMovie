package com.cinema.emovie.ui.movie_detail.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cinema.emovie.common.*
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

    private fun showMovieData() = with(binding) {
        getPosterImage()
        showTitle()
        showYear()
        showLanguage()
        showVoteAverage()
    }

    private fun getPosterImage() {
        val localStorageBitmap = movieItem?.posterUrl?.getFromLocalStorage()
        localStorageBitmap?.let {
            binding.imageViewMovieDetailPoster.loadFromBitmap(this, it)
        }
    }

    private fun showTitle() = with(movieItem) {
        binding.textViewMovieDetailTitle.showText(
            this?.title ?: this?.originalTitle ?: this?.originalName
        )
    }

    private fun showYear() = with(movieItem) {
        val date = this?.releaseDate ?: this?.firstAirDate
        binding.textViewMovieDetailYear.showText(date?.getYear())
    }

    private fun showLanguage() {
        binding.textViewMovieDetailLanguage.showText(movieItem?.originalLanguage)
    }

    private fun showVoteAverage() {
        binding.textViewMovieDetailVoteAverage.showText(movieItem?.voteAverage?.toOneDecimal())
    }

}
