package com.issen.ebooker.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.issen.ebooker.R

@BindingAdapter("thumbnail")
fun setThumbnail(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).placeholder(R.drawable.ic_search).into(view)
}

@BindingAdapter("roundedRatingText")
fun setRoundedRatingText(view: TextView, rating: Float) {
    view.text = view.context.resources.getString(R.string.rating_text, "%.2f".format(rating))
}