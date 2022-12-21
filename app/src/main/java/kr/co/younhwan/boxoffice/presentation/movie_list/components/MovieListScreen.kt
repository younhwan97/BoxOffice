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
    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(10000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                animationSpec = tween(600)
            )
        }
    }

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
                    .padding(
                        top = 40.dp,
                        bottom = 40.dp
                    ),
                count = state.movies.size,
                contentPadding = PaddingValues(horizontal = 32.dp),
            ) { pageIndex ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            // Image Scale
                            val pageOffset = calculateCurrentOffsetForPage(pageIndex).absoluteValue

                            lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                        }
                        .padding(
                            start = 2.dp,
                            end = 2.dp,
                        ),
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color.Transparent
                ) {
                    Card {
                        val movie = state.movies[pageIndex]

                        Box {
                            val customView = KenBurnsView(LocalContext.current).also { imageView ->
                                imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                                imageView.load(movie.image)
                            }
                            AndroidView(
                                factory = { customView },
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(Color(android.graphics.Color.parseColor("#80000000")))
                            ) {}

                            // Text
                            Column(
                                Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp)
                            ) {

                                Text(
                                    text = movie.title,
                                    style = MaterialTheme.typography.h5,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}