package suvorov.kinopoisk.domain.repository

import suvorov.kinopoisk.domain.common.Result
import suvorov.kinopoisk.domain.entity.Film

interface FilmsRepository {
    suspend fun getFilms(): Result<List<Film>>
}