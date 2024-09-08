package com.example.atlys.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.atlys.ui.screen.MainScreens
import com.example.atlys.ui.screen.MovieDetailsScreen
import com.example.atlys.ui.screen.MovieListScreen
import com.example.atlys.ui.viewmodel.MainViewModel

@Composable
fun MainNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainScreens.MovieList.route
    ) {
        composable(MainScreens.MovieList.route) {
            MovieListScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable(MainScreens.MovieDetail.route) {
            MovieDetailsScreen(navController = navController, movie = mainViewModel.selectedMovie)
        }
    }
}