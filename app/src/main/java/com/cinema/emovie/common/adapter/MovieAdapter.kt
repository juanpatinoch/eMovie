package com.cinema.emovie.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cinema.emovie.common.loadUrl
import com.cinema.emovie.common.toDP
import com.cinema.emovie.databinding.ItemPosterBinding
import com.cinema.emovie.domain.model.Movie


class MovieAdapter(
    private val onItemClick: (movieItem: Movie) -> Unit
) : ListAdapter<Movie, MovieAdapter.MovieAdapterViewHolder>(ItemAdapterDiffCallback()) {

    private lateinit var context: Context

    class ItemAdapterDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id
    }

    inner class MovieAdapterViewHolder(
        private val binding: ItemPosterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) = with(binding) {
            val item = getItem(position)

            val startMargin = if (position == 0) 25 else 0
            val endMargin = if (position == itemCount - 1) 25 else 16

            setCustomMargin(cardViewPoster, startMargin, endMargin)

            item.posterUrl?.let {
                imageViewPoster.loadUrl(it)
            }
            cardViewPoster.setOnClickListener {
                onItemClick.invoke(item)
            }
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

            if (view.layoutParams is MarginLayoutParams) {
                val p = view.layoutParams as MarginLayoutParams
                p.setMargins(startMarginDP, topMarginDP, endMarginDP, bottomMarginDP)
                view.requestLayout()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = ItemPosterBinding.inflate(inflater, parent, false)
        return MovieAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieAdapterViewHolder, position: Int) {
        holder.bind(position)
    }

}