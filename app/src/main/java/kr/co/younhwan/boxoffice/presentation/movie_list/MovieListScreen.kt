package kr.co.younhwan.boxoffice.presentation.movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kr.co.younhwan.boxoffice.presentation.Screen
import kr.co.younhwan.boxoffice.presentation.support.LoadingScreen
import kr.co.younhwan.boxoffice.presentation.movie_list.components.MovieListItem
import kr.co.younhwan.boxoffice.presentation.support.ErrorScreen
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Column {
        // Header
        Text(
            text = "Top",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 16.dp, top = 40.dp),
            fontWeight = FontWeight.Black
        )
        Text(
            text = "Box Office",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 16.dp),
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Black
        )

        // Content
        if (state.isLoading) {
            // Lading
            LoadingScreen()
        } else if (state.movies.isNotEmpty()) {
            // Success
            val movies = state.movies
            val pagerState = rememberPagerState()

            HorizontalPager(
                state = pagerState,
                count = movies.size,
                contentPadding = PaddingValues(horizontal = 40.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 48.dp),
            ) { index ->
                MovieListItem(
                    movie = movies[index],
                    pageOffset = calculateCurrentOffsetForPage(index).absoluteValue,
                    onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route + "/${movies[index].id}")
                    }
                )
            }
        } else {
            // Error
            ErrorScreen()
        }
    }
}