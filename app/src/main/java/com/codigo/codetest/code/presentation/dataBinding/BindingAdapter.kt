package com.codigo.codetest.code.presentation.dataBinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Set image url binding adapter
 *
 * @param imageView
 * @param url
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        Glide.with(imageView)
            .load(imageUrl)
            .into(imageView)
    }
}
