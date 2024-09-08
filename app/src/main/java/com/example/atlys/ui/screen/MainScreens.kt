package com.example.atlys.ui.screen

import com.example.atlys.helper.Constants

sealed class MainScreens(val route: String) {
    data object MovieList : MainScreens(Constants.MOVIE_LIST)
    data object MovieDetail : MainScreens(Constants.MOVIE_DETAIL)
}