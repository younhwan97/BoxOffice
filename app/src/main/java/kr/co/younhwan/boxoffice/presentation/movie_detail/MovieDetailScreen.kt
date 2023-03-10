package kr.co.younhwan.boxoffice.presentation.movie_detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import kr.co.younhwan.boxoffice.domain.model.MovieDetail
import kr.co.younhwan.boxoffice.presentation.support.LoadingScreen
import kr.co.younhwan.boxoffice.presentation.movie_detail.components.GenreTag
import kr.co.younhwan.boxoffice.presentation.movie_detail.components.PosterModal
import kr.co.younhwan.boxoffice.presentation.support.ErrorScreen

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    if (state.isLoading) {
        // Loading
        LoadingScreen()
    } else if (state.movie != null) {
        // Success
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            state.movie.let { movieDetail ->

                MovieDetailPoster(movieDetail)
                MovieDetailHeader(movieDetail)
                Spacer(modifier = Modifier.height(16.dp))
                MovieDetailPlot(movieDetail)
                Spacer(modifier = Modifier.height(32.dp))
                MovieDetailMember(movieDetail)
                Spacer(modifier = Modifier.height(16.dp))
                MovieDetailGenre(movieDetail)
                Spacer(modifier = Modifier.height(16.dp))
                MovieDetailPosters(movieDetail)
                Spacer(modifier = Modifier.height(32.dp))

            }
        }
    } else {
        // Error
        ErrorScreen()
    }
}

@Composable
fun MovieDetailPoster(
    movieDetail: MovieDetail
) {
    Box(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth()
    ) {
        // Poster
        CoilImage(
            imageModel = { movieDetail.image },
            modifier = Modifier
                .fillMaxSize(),
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            ),
            failure = {
                Text(
                    text = "image request failed.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.fillMaxSize()
                )
            },
            component = rememberImageComponent {
                +CrossfadePlugin(
                    duration = 550
                )
            }
        )

        // Gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colors.background.copy(alpha = 0.0f),
                            MaterialTheme.colors.background.copy(alpha = 0.0f),
                            MaterialTheme.colors.background.copy(alpha = 0.0f),
                            MaterialTheme.colors.background.copy(alpha = 0.0f),
                            MaterialTheme.colors.background.copy(alpha = 0.0f),
                            MaterialTheme.colors.background.copy(alpha = 1.0f)
                        ),
                    )
                ),
        )
    }
}

@Composable
fun MovieDetailHeader(
    movieDetail: MovieDetail
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column() {
            Text(
                text = movieDetail.title,
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(0.80f)
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = movieDetail.releaseDate,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = movieDetail.runtimeStr,
                    style = MaterialTheme.typography.body2,
                    color = Color.Gray,
                    textAlign = TextAlign.Start,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFF2BB15),
                modifier = Modifier.size(16.dp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = movieDetail.imDbRating,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun MovieDetailPlot(
    movieDetail: MovieDetail,
) {
    Text(
        text = movieDetail.plot,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun MovieDetailMember(
    movieDetail: MovieDetail
) {
    Divider(
        color = Color.Gray,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .height(1.dp)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Director",
            style = MaterialTheme.typography.h4,
            fontSize = 14.sp
        )

        Text(
            text = movieDetail.directors,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    Divider(
        color = Color.Gray,
        modifier = Modifier
            .padding(16.dp)
            .height(1.dp)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Writer",
            style = MaterialTheme.typography.h4,
            fontSize = 14.sp
        )

        Text(
            text = movieDetail.writers,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    Divider(
        color = Color.Gray,
        modifier = Modifier
            .padding(16.dp)
            .height(1.dp)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        Text(
            text = "Stars",
            style = MaterialTheme.typography.h4,
            fontSize = 14.sp
        )

        Text(
            text = movieDetail.stars,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body2,
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    Divider(
        color = Color.Gray,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            .height(1.dp)
    )
}

@Composable
fun MovieDetailGenre(
    movieDetail: MovieDetail
) {
    Text(
        text = "genre",
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        movieDetail.genreList.forEach { genre ->
            GenreTag(tag = genre.value)
        }
    }
}

@Composable
fun MovieDetailPosters(
    movieDetail: MovieDetail
) {
    if (movieDetail.posters.isNotEmpty()) {
        // ?????? 10??? ??????
        val posters = if (movieDetail.posters.size >= 10) movieDetail.posters.subList(0, 10) else movieDetail.posters

        Text(
            text = "Posters",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(posters.size) { index ->
                val posterState = remember { mutableStateOf(false) }

                if (posterState.value) {
                    PosterModal(poster = posters[index], openPosterModal = posterState)
                }

                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .size(150.dp)
                        .clickable {
                            posterState.value = !posterState.value
                        }
                ) {
                    CoilImage(
                        imageModel = { posters[index].link },
                        modifier = Modifier
                            .fillMaxSize(),
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                        ),
                        failure = {
                            Text(
                                text = "image request failed.",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.body2,
                                modifier = Modifier.fillMaxSize()
                            )
                        },
                        component = rememberImageComponent {
                            +CrossfadePlugin(
                                duration = 550
                            )
                            +ShimmerPlugin(
                                baseColor = Color(0xFF424242),
                                highlightColor = Color(0xA3C2C2C2)
                            )
                        }
                    )
                }
            }
        }
    }
}