package com.cinema.emovie.ui.home.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cinema.emovie.common.*
import com.cinema.emovie.databinding.ItemHorizontalMovieBinding
import com.cinema.emovie.domain.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HorizontalMovieAdapter(
    private val onItemClick: (movieItem: Movie) -> Unit
) : ListAdapter<Movie, HorizontalMovieAdapter.HorizontalMovieAdapterViewHolder>(
    ItemAdapterDiffCallback()
) {
    private lateinit var context: Context

    class ItemAdapterDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
    }

    inner class HorizontalMovieAdapterViewHolder(
        private val binding: ItemHorizontalMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) = with(binding) {
            val item = getItem(position)

            imageViewPoster.clear(context)

            val startMargin = if (position == 0) 25 else 0
            val endMargin = if (position == itemCount - 1) 25 else 16

            setCustomMargin(cardViewPoster, startMargin, endMargin)

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

        private fun setCustomMargin(
            view: View,
            startMargin: Int,
            endMargin: Int,
            topMargin: Int = 8,
            bottomMargin: Int = 8
        ) {
            val startMarginDP = startMargin.toDP(context)
            val endMarginDP = endMargin.toDP(context)
            val topMarginDP = topMargin.toDP(context)
            val bottomMarginDP = bottomMargin.toDP(context)

            if (view.layoutParams is ViewGroup.MarginLayoutParams) {
                val p = view.layoutParams as ViewGroup.MarginLayoutParams
                p.setMargins(startMarginDP, topMarginDP, endMarginDP, bottomMarginDP)
                view.requestLayout()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalMovieAdapterViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = ItemHorizontalMovieBinding.inflate(inflater, parent, false)
        return HorizontalMovieAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: HorizontalMovieAdapterViewHolder, position: Int) {
        holder.bind(position)
    }
}