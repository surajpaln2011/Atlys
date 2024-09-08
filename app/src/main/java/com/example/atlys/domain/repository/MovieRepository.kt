package com.example.atlys.domain.repository

import com.example.atlys.domain.model.MovieList
import com.example.atlys.base.Resource

interface MovieRepository {
    suspend fun getMovies(language: String): Resource<MovieList>

    suspend fun searchMovie(params: Map<String, String>): Resource<MovieList>
}