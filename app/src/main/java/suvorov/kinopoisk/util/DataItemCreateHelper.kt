package suvorov.kinopoisk.util

import suvorov.kinopoisk.domain.entity.Film
import suvorov.kinopoisk.domain.entity.DataItem
import suvorov.kinopoisk.domain.entity.Genre
import suvorov.kinopoisk.domain.entity.Header
import suvorov.kinopoisk.util.Constants.FILMS
import suvorov.kinopoisk.util.Constants.GENRES

object DataItemCreateHelper {
    private fun getGenres(films: List<Film>): List<Genre> {
        return arrayListOf<Genre>().apply {
            films.forEach { film ->
                film.genres?.forEach { genre ->
                    if(!contains(Genre(genre))) {
                        add(Genre(genre))
                    }
                }
            }
        }
    }

    fun createDataItem(films: List<Film>): List<DataItem> {
        return arrayListOf<DataItem>().apply {
            add(Header(GENRES))
            addAll(getGenres(films))
            add(Header(FILMS))
            addAll(films)
        }
    }
}