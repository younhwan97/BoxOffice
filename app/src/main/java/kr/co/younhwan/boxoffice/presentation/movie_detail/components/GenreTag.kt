package kr.co.younhwan.boxoffice.presentation.movie_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GenreTag(
    tag: String
) {
    Chip(
        onClick = {},
        colors = ChipDefaults.chipColors(Color.Transparent),
        border = BorderStroke(width = 1.dp, color = Color.DarkGray)
    ) {
        Text(
            text = tag,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview
@Composable
fun PreviewGenreTag() {
    GenreTag(tag = "test")
}