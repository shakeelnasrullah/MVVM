package com.pak.mvvm

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadFromUrl")
fun ImageView.loadFromUrl(url : String){
    Glide.with(this.context).load(url).into(this)
}