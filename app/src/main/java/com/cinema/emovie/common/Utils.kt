package com.cinema.emovie.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.cinema.emovie.common.cache.LruCacheHandler
import java.text.SimpleDateFormat
import java.util.*


fun Int.toDP(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources
        .displayMetrics
).toInt()

fun String.toBitmap(context: Context): Bitmap? = try {
    Glide.with(context).asBitmap().load(this).submit().get()
} catch (e: Exception) {
    null
}

fun String.getFromLocalStorage(): Bitmap? {
    return LruCacheHandler.instance?.retrieveBitmapFromCache(this)
}

fun Bitmap.saveInLocalStorage(name: String) {
    LruCacheHandler.instance?.saveBitmapToCache(name, this)
}

fun ImageView.loadFromBitmap(context: Context, bitmap: Bitmap) {
    Glide.with(context)
        .load(bitmap)
        .centerCrop()
        .into(this)
}

fun ImageView.clear(context: Context) {
    Glide.with(context)
        .clear(this)
}

fun TextView.showText(text: String?) {
    text?.let {
        this.visibility = View.VISIBLE
        this.text = it
    }
}

@SuppressLint("SimpleDateFormat")
fun String.getYear(): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val date = sdf.parse(this)
    val calendar = Calendar.getInstance()
    date?.let {
        calendar.time = it
        return calendar.get(Calendar.YEAR).toString()
    } ?: run {
        return null
    }
}

fun Double.toOneDecimal() = String.format("%.1f", this).replace(",", ".")