package suvorov.kinopoisk.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import suvorov.kinopoisk.R

fun ImageView.setImage(uri: String) {
    Glide.with(this)
        .load(uri)
        .placeholder(R.drawable.image_not_loaded)
        .error(R.drawable.image_not_found)
        .fallback(R.drawable.image_not_found)
        .into(this)
}