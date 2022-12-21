package kr.co.younhwan.boxoffice.presentation.movie_list.components

import android.widget.ImageView
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.load
import com.flaviofaria.kenburnsview.KenBurnsView
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kr.co.younhwan.boxoffice.presentation.movie_list.MovieListViewModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val pagerState = rememberPagerState()

    // Auto Scroll
//    LaunchedEffect(Unit) {
//        while (true) {
//            yield()
//            delay(10000)
//            tween<Float>(600)
//            pagerState.animateScrollToPage(
//                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
//            )
//        }
//    }

    if (state.isLoading) {

    } else {
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
                count = state.movies.size,
                contentPadding = PaddingValues(horizontal = 32.dp),
                userScrollEnabled = true
            ) { pageIndex ->
                
                MovieListItem(
                    movie = state.movies[pageIndex],
                    pageOffset = calculateCurrentOffsetForPage(pageIndex).absoluteValue,
                    onItemClick = {}
                )
            }
        }
    }
}