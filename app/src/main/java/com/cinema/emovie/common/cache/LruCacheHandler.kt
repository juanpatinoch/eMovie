package com.cinema.emovie.common.cache

import android.graphics.Bitmap
import android.util.LruCache

class LruCacheHandler private constructor() {

    private val lru: LruCache<Any, Any> = LruCache(1024)

    fun saveBitmapToCache(key: String, bitmap: Bitmap?) {
        bitmap?.let {
            instance?.lru?.put(key, it)
        }
    }

    fun retrieveBitmapFromCache(key: String): Bitmap? {
        return instance?.lru?.get(key) as Bitmap?
    }

    companion object {
        var instance: LruCacheHandler? = null
            get() {
                if (field == null) {
                    field = LruCacheHandler()
                }
                return field
            }
            private set
    }
}