package kr.co.younhwan.boxoffice.presentation.movie_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kr.co.younhwan.boxoffice.R
import kr.co.younhwan.boxoffice.domain.model.Movie

@Composable
fun MovieListItem(
    movie: Movie,
    onItemClick: () -> Unit
) {
    GlideImage(
        imageModel = movie.image,
        // Crop, Fit, Inside, FillHeight, FillWidth, None
        contentScale = ContentScale.Crop,
        // shows an image with a circular revealed animation.
        circularReveal = CircularReveal(duration = 250)
    )
}