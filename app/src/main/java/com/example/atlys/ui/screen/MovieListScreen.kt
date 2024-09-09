package com.example.atlys.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.atlys.ui.viewmodel.MainViewModel
import com.example.atlys.ui.viewmodel.MovieListingViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce

@Composable
fun MovieListScreen(
    navController: NavController,
    mainViewModel: MainViewModel,
    viewModel: MovieListingViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        SearchBar(viewModel = viewModel)

        if (isLoading) {
            Loader()
        } else {
            MovieGrid(viewModel, navController = navController, mainViewModel)
        }
    }
}

@OptIn(FlowPreview::class)
@Composable
fun SearchBar(viewModel: MovieListingViewModel) {
    val searchFlow = remember { MutableStateFlow("") }
    val searchText by searchFlow.collectAsState()

    LaunchedEffect(searchText) {
        searchFlow
            .debounce(500)
            .collectLatest { query ->
                if (query.isEmpty() || query.length > 3) {
                    viewModel.searchMovie(query)
                }
            }
    }

    OutlinedTextField(
        value = searchText,
        label = { Text(text = "Search movies") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        onValueChange = { searchFlow.value = it }
    )
}

@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MovieGrid(viewModel: MovieListingViewModel, navController: NavController, mainViewModel: MainViewModel) {
    val movieList by viewModel.movieList.collectAsStateWithLifecycle()

    LazyVerticalGrid(
        modifier = Modifier.padding(vertical = 16.dp),
        columns = GridCells.Fixed(2),
        state = rememberLazyGridState(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movieList.size) { position ->
            MovieGridView(movieList[position], navController = navController, mainViewModel)
        }
    }
}

@Composable
@Preview
fun MovieListScreenPreview() {
    MovieListScreen(navController = rememberNavController(), mainViewModel = hiltViewModel())
}

