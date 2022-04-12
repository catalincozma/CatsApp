package com.example.catapplication.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadFromUrl(
    imageUrl: String,
    diskCacheStrategy: DiskCacheStrategy? = null,
    skipMemCache: Boolean = false,
    placeholder: Drawable? = null,
    requestOptions: RequestOptions = RequestOptions()
) {
    var ro = requestOptions

    diskCacheStrategy?.let {
        ro = ro.diskCacheStrategy(diskCacheStrategy)
    }


    ro = ro.skipMemoryCache(skipMemCache)

    placeholder?.let {
        ro = ro.placeholder(it)
    }

    Glide.with(this).load(imageUrl).apply(ro).into(this)
}

