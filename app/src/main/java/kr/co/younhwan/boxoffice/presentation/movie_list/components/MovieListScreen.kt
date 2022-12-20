package kr.co.younhwan.boxoffice.presentation.movie_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.co.younhwan.boxoffice.presentation.movie_list.MovieListViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    val state2 = rememberLazyListState()

    LazyRow(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        state = state2,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = state2)
    ) {
        items(200) {
            Text(it.toString(), fontSize = 32.sp)
        }
    }
}