package kr.co.younhwan.boxoffice.data.remote.dto

import kr.co.younhwan.boxoffice.domain.model.Movie

data class MovieDto(
    val gross: String,
    val id: String,
    val image: String,
    val rank: String,
    val title: String,
    val weekend: String,
    val weeks: String
)

fun MovieDto.toMovie(): Movie {
    return Movie(
        id, image, rank, title, weekend, weeks, gross
    )
}