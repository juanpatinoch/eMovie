package com.cinema.emovie.common

import android.content.Context
import android.graphics.Bitmap
import android.util.TypedValue
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.cinema.emovie.common.cache.LruCacheHandler


fun Int.toDP(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources
        .displayMetrics
).toInt()

fun String.toBitmap(context: Context): Bitmap {
    return Glide.with(context).asBitmap().load(this).submit().get()
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
