package suvorov.kinopoisk.data.api

import retrofit2.Response
import retrofit2.http.GET
import suvorov.kinopoisk.data.api.model.FilmsListResponse

interface ApiService {
    @GET("sequeniatesttask/films.json")
    suspend fun getFilms(): Response<FilmsListResponse>
}