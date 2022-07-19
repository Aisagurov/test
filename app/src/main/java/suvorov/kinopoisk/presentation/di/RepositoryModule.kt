package suvorov.kinopoisk.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import suvorov.kinopoisk.data.api.ApiService
import suvorov.kinopoisk.data.repository.FilmsRepositoryImpl
import suvorov.kinopoisk.domain.repository.FilmsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideFilmsRepository(service: ApiService): FilmsRepository {
        return FilmsRepositoryImpl(service)
    }
}