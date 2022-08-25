package com.cinema.emovie.ui.movie_detail.view

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cinema.emovie.common.*
import com.cinema.emovie.databinding.ActivityMovieDetailBinding
import com.cinema.emovie.domain.model.Movie
import com.cinema.emovie.ui.movie_detail.status.MovieDetailStatus
import com.cinema.emovie.ui.movie_detail.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel: MovieDetailViewModel by viewModels()

    private var movieItem: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupAppBar()
        setupSizes()
        getInfoArguments()
        subscribeObserver()
        getInitData()
        showMovieData()
        setListeners()
    }

    private fun setupBinding() {
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupAppBar() = with(binding) {
        layoutAppbar.imageViewAppbarLogo.visibility = View.GONE
        layoutAppbar.imageViewAppbarBack.visibility = View.VISIBLE
    }

    private fun setupSizes() {
        val metrics = Resources.getSystem().displayMetrics
        val height = metrics.widthPixels.coerceAtMost(metrics.heightPixels)
        val params = binding.transparentView.layoutParams
        params.height = (height * 70) / 70
        binding.transparentView.layoutParams = params
    }

    private fun subscribeObserver() {
        viewModel.movieDetailStatus.observe(this) {
            when (it) {
                is MovieDetailStatus.SuccessGetGenre -> setGenreData(it.genres)
                is MovieDetailStatus.Error -> {
                    //TODO: Pendiente implementar esto
                }
            }
        }
    }

    private fun getInitData() {
        viewModel.getGenre(movieItem?.genreIds)
    }

    private fun setListeners() {
        binding.layoutAppbar.imageViewAppbarBack.setOnClickListener {
            finish()
        }
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
        showOverview()
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

    private fun showOverview() {
        binding.textViewMovieDetailOverview.showText(movieItem?.overview)
    }

    private fun setGenreData(genres: String?) {
        binding.textViewMovieDetailGenres.showText(genres)
    }

}
