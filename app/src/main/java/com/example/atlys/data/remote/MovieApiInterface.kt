package com.example.atlys.data.remote

import com.example.atlys.data.model.MovieDto
import com.example.atlys.data.model.MovieListDto
import com.example.atlys.helper.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieApiInterface {

    @GET("trending/movie/day")
    suspend fun getMovies(
        @Query("language") language: String,
        @Header("Authorization") auth: String = Constants.API_KEY
    ): Response<MovieListDto>

    @GET("search/movie")
    suspend fun searchMovie(
        @QueryMap params: Map<String, String>,
        @Header("Authorization") auth: String = Constants.API_KEY
    ): Response<MovieListDto>
}