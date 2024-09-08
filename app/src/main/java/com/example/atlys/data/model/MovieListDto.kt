package com.example.atlys.data.model

data class MovieListDto(
    val page: Int?,
    val results: List<MovieDto>?,
    val total_pages: Int?,
    val total_results: Int?
)