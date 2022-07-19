package suvorov.kinopoisk.presentation.common.clickListener

import suvorov.kinopoisk.domain.entity.Genre

interface OnGenreClickListener {
    fun onGenreClick(genre: Genre)
}