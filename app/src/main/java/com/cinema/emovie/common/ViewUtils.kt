package com.cinema.emovie.common

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String) {
    Picasso.get()
        .load(url)
        .fit()
        .into(this)
}