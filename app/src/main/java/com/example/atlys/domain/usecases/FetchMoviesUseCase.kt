package com.example.atlys.domain.usecases

import com.example.atlys.domain.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(language: String) = withContext(Dispatchers.IO) {
        return@withContext movieRepository.getMovies(language)
    }
}