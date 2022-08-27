package com.cinema.emovie.ui.home.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cinema.emovie.common.*
import com.cinema.emovie.databinding.ItemVerticalMovieBinding
import com.cinema.emovie.domain.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VerticalMovieAdapter(
    private val onItemClick: (movieItem: Movie) -> Unit
) : ListAdapter<Movie, VerticalMovieAdapter.VerticalMovieAdapterViewHolder>(
    ItemAdapterDiffCallback()
) {

    private lateinit var context: Context

    class ItemAdapterDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
    }

    inner class VerticalMovieAdapterViewHolder(
        private val binding: ItemVerticalMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) = with(binding) {
            val item = getItem(position)

            imageViewPoster.clear(context)

            item.posterUrl?.let { url ->
                val localStorageBitmap = url.getFromLocalStorage()
                localStorageBitmap?.let {
                    imageViewPoster.loadFromBitmap(context, it)
                } ?: run {
                    getBitmapImage(url)
                }
            }

            cardViewPoster.setOnClickListener {
                onItemClick.invoke(item)
            }
        }

        private fun getBitmapImage(url: String) = CoroutineScope(Dispatchers.IO).launch {
            val bitmap = url.toBitmap(context)
            bitmap?.let {
                setItemImage(it)
                it.saveInLocalStorage(url)
            }
        }

        private fun setItemImage(bitmap: Bitmap) = CoroutineScope(Dispatchers.Main).launch {
            binding.imageViewPoster.loadFromBitmap(context, bitmap)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VerticalMovieAdapterViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = ItemVerticalMovieBinding.inflate(inflater, parent, false)
        return VerticalMovieAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: VerticalMovieAdapterViewHolder, position: Int) {
        holder.bind(position)
    }

}