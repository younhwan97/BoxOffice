package kr.co.younhwan.boxoffice.presentation.movie_detail

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.load
import com.flaviofaria.kenburnsview.KenBurnsView
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

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

            // Poster & Gradient
            Box(
                modifier = Modifier
                    .height(350.dp)
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
                        +CircularRevealPlugin(
                            duration = 350
                        )
                    },
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


            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = movieDetail.title,
                style = MaterialTheme.typography.h5,
                color = Color.White,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

        }
    }
}

@Preview
@Composable
fun PreviewMovieDetail() {
    MovieDetailScreen()
}