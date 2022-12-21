package kr.co.younhwan.boxoffice.presentation.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
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
import com.skydoves.landscapist.coil.CoilImage
import kr.co.younhwan.boxoffice.domain.model.MovieDetail
import kr.co.younhwan.boxoffice.presentation.movie_detail.components.GenreTag

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        state.movie?.let { movieDetail ->

            MovieDetailPoster(movieDetail)

            Spacer(modifier = Modifier.height(8.dp))

            MovieDetailHeader(movieDetail)

            Spacer(modifier = Modifier.height(16.dp))

            MovieDetailPlot(movieDetail)

            Spacer(modifier = Modifier.height(16.dp))

            MovieDetailGenre(movieDetail)

            Spacer(modifier = Modifier.height(16.dp))

            MovieDetailMember(movieDetail)

            Spacer(modifier = Modifier.height(16.dp))
            
            MovieDetailTrailer(movieDetail)
        }
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
            }
        )

        // Gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.DarkGray.copy(alpha = 0.0f),
                            Color.DarkGray.copy(alpha = 0.0f),
                            Color.DarkGray.copy(alpha = 0.0f),
                            Color.DarkGray.copy(alpha = 0.0f),
                            Color.DarkGray.copy(alpha = 0.0f),
                            Color.DarkGray.copy(alpha = 1.0f)
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Text(
                text = movieDetail.title,
                style = MaterialTheme.typography.h3,
                color = Color.White,
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
                color = Color.White,
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
    movieDetail: MovieDetail
) {
    Text(
        text = movieDetail.plot,
        style = MaterialTheme.typography.body2,
        color = Color.White,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
fun MovieDetailGenre(
    movieDetail: MovieDetail
) {
    Text(
        text = "genre",
        color = Color.White,
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

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
            color = Color.White,
            fontSize = 14.sp
        )

        Text(
            text = movieDetail.directors,
            textAlign = TextAlign.End,
            color = Color.White,
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
            color = Color.White,
            fontSize = 14.sp
        )

        Text(
            text = movieDetail.writers,
            textAlign = TextAlign.End,
            color = Color.White,
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
            color = Color.White,
            fontSize = 14.sp
        )

        Text(
            text = movieDetail.stars,
            textAlign = TextAlign.End,
            color = Color.White,
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
}

@Composable
fun MovieDetailTrailer(
    movieDetail: MovieDetail
) {
    Text(
        text = "Trailer",
        color = Color.White,
        style = MaterialTheme.typography.h3,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}