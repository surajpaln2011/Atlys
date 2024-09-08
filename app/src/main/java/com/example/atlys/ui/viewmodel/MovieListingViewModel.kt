package com.example.atlys.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atlys.base.Resource
import com.example.atlys.domain.model.Movie
import com.example.atlys.domain.usecases.FetchMoviesUseCase
import com.example.atlys.domain.usecases.SearchMovieUseCase
import com.example.atlys.helper.ApiParams
import com.example.atlys.helper.Constants
import com.example.atlys.helper.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListingViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
): ViewModel() {

    private val _movieList = MutableStateFlow<List<Movie>>(emptyList())
    val movieList: StateFlow<List<Movie>> = _movieList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val originalMovieList = mutableListOf<Movie>()

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        _isLoading.update(this) { true }
        val response = fetchMoviesUseCase(Constants.MOVIE_LANGUAGE_US)
        if (response is Resource.Success) {
            val list = response.data?.results.orEmpty()
            _movieList.update(this) { list.toMutableList() }
            originalMovieList.addAll(list)
        }
        _isLoading.update(this) { false }
    }

    fun searchMovie(query: String) = viewModelScope.launch {
        if (query.isEmpty()) {
            _movieList.update(this) { originalMovieList }
            return@launch
        }
        _isLoading.update(this) { true }
        val params = mapOf(
            ApiParams.QUERY to query
        )
        val response = searchMovieUseCase(params)
        if (response is Resource.Success) {
            _movieList.update(this) { response.data?.results.orEmpty() }
        }
        _isLoading.update(this) { false }
    }
}