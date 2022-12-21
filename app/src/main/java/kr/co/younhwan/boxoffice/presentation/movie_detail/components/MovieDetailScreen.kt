package kr.co.younhwan.boxoffice.presentation.movie_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.younhwan.boxoffice.presentation.movie_detail.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        state.movie?.let { movieDetail ->

        }
    }
}