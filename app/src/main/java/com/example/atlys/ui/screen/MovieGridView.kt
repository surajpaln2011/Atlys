package com.example.atlys.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.atlys.BuildConfig
import com.example.atlys.R
import com.example.atlys.domain.model.Movie
import com.example.atlys.ui.viewmodel.MainViewModel

@Composable
fun MovieGridView(
    movie: Movie,
    navController: NavController,
    mainViewModel: MainViewModel
) {
    MovieGridViewImpl(
        movie = movie,
        onMovieClick = { clickedMovie ->
            mainViewModel.selectedMovie = clickedMovie
            navController.navigate(MainScreens.MovieDetail.route)
        }
    )
}

@Composable
fun MovieGridViewImpl(
    movie: Movie,
    onMovieClick : (Movie) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onMovieClick(movie)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth(),
            model = "${BuildConfig.IMAGE_BASE_URL}${movie.poster_path}",
            contentDescription = movie.title,
            error = painterResource(R.drawable.blank_image),
            contentScale = ContentScale.FillWidth
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = movie.title.toString(),
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}