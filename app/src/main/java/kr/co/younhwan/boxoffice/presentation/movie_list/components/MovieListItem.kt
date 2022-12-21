package kr.co.younhwan.boxoffice.presentation.movie_list.components

import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import coil.load
import com.flaviofaria.kenburnsview.KenBurnsView
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kr.co.younhwan.boxoffice.domain.model.Movie
import kotlin.math.absoluteValue

@Composable
fun MovieListItem(
    movie: Movie,
    pageOffset: Float,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                // Image Scale
                lerp(
                    start = 0.85f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                ).also { scale ->
                    scaleX = scale
                    scaleY = scale
                }

                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            },
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.Transparent
    ) {
        Card {
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

                // Rank
                Text(
                    text = movie.rank,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp),
                )

                // Title
                Text(
                    text = movie.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                )
            }
        }
    }
}