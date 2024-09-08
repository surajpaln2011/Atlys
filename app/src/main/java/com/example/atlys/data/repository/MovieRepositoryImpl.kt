package com.example.atlys.data.repository

import com.example.atlys.base.BaseRepository
import com.example.atlys.base.Resource
import com.example.atlys.data.remote.MovieApiInterface
import com.example.atlys.domain.model.MovieList
import com.example.atlys.domain.repository.MovieRepository
import com.example.atlys.helper.toDomainLayer

class MovieRepositoryImpl(
    private val movieApiInterface: MovieApiInterface
): MovieRepository, BaseRepository() {

    override suspend fun getMovies(language: String): Resource<MovieList> {
        return networkCall(
            apiCall = { movieApiInterface.getMovies(language) },
            mapper = { it.toDomainLayer() }
        )
    }

    override suspend fun searchMovie(params: Map<String, String>): Resource<MovieList> {
        return networkCall(
            apiCall = { movieApiInterface.searchMovie(params) },
            mapper = { it.toDomainLayer() }
        )
    }
}