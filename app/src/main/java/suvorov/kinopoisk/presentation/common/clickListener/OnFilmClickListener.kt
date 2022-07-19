package suvorov.kinopoisk.presentation.common.clickListener

import suvorov.kinopoisk.domain.entity.Film

interface OnFilmClickListener {
    fun onFilmClick(film: Film)
}