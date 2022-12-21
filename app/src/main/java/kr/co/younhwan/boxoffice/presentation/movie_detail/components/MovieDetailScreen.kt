package kr.co.younhwan.boxoffice.presentation.movie_detail.components

import android.widget.ImageView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import coil.load
import com.flaviofaria.kenburnsview.KenBurnsView
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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    val customView = KenBurnsView(LocalContext.current).also { imageView ->
                        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
                        imageView.load(movieDetail.image)
                    }
                    AndroidView(
                        factory = { customView },
                        modifier = Modifier.fillMaxSize()
                    )

                    Text(
                        text = movieDetail.title
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMovieDetail() {
    MovieDetailScreen()
}