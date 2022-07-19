package suvorov.kinopoisk.data.repository

import suvorov.kinopoisk.data.api.ApiService
import suvorov.kinopoisk.data.mapper.FilmApiMapper
import suvorov.kinopoisk.domain.repository.FilmsRepository
import suvorov.kinopoisk.domain.common.Result
import suvorov.kinopoisk.domain.entity.Film
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(private val service: ApiService): FilmsRepository {

    override suspend fun getFilms(): Result<List<Film>> {
        try {
            val response = service.getFilms()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body.films.map { FilmApiMapper.toFilm(it) })
            } else if (response.errorBody() != null) {
                return Result.Error(response.errorBody().toString())
            }
            return Result.Error("Unexpected error")
        } catch (e: Exception) {
            return Result.Error(e.message ?: e.toString())
        }
    }
}