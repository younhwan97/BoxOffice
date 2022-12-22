package kr.co.younhwan.boxoffice.presentation.movie_detail.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import kr.co.younhwan.boxoffice.data.remote.dto.Poster

@Composable
fun PosterModal(
    poster: Poster,
    openPosterModal: MutableState<Boolean>
) {
    Dialog(
        onDismissRequest = { openPosterModal.value = !openPosterModal.value }
    ) {
        PosterModalUI(
            poster = poster
        )
    }
}


@Composable
fun PosterModalUI(
    poster: Poster,
) {
    Card(
        elevation = 8.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Box {
            CoilImage(
                imageModel = { poster.link },
                modifier = Modifier
                    .height(500.dp)
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


