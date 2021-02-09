package com.issen.ebooker.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.issen.ebooker.R

@BindingAdapter("thumbnail")
fun setThumbnail(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).placeholder(R.drawable.ic_search).into(view)
}