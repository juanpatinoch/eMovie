package com.cinema.emovie.common

import android.content.Context
import android.util.TypedValue

fun Int.toDP(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources
        .displayMetrics
).toInt()