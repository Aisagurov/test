package suvorov.kinopoisk.data.mapper

import suvorov.kinopoisk.data.api.model.FilmApi
import suvorov.kinopoisk.domain.entity.Film

object FilmApiMapper {
    fun toFilm(film: FilmApi): Film {
        return Film(
        film.id,
        film.localizedName,
        film.name,
        film.year,
        film.rating,
        film.imageUrl,
        film.description,
        film.genres
        )
    }
}