package kr.co.younhwan.boxoffice.presentation.movie_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kr.co.younhwan.boxoffice.presentation.movie_list.MovieListViewModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val pagerState = rememberPagerState()

    if (!state.isLoading) {
        val movies = state.movies

        Column {
            Text(
                text = "Discover",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(start = 16.dp, top = 40.dp),
                fontWeight = FontWeight.Black
            )
            Text(
                text = "Places",
                style = MaterialTheme.typography.h4,
                modifier = Modifier.padding(start = 16.dp),
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Black
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 40.dp),
                count = movies.size,
                contentPadding = PaddingValues(horizontal = 32.dp),
                userScrollEnabled = true
            ) { index ->

                MovieListItem(
                    movie = movies[index],
                    pageOffset = calculateCurrentOffsetForPage(index).absoluteValue,
                    onItemClick = {}
                )
            }
        }
    }
}