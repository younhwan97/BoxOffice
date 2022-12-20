package kr.co.younhwan.boxoffice.data.remote.dto

import kr.co.younhwan.boxoffice.domain.model.Movie

data class MovieDto(
    val id: String,
    val rank: String,
    val title: String,
    val image: String,
    val weekend: String,
    val gross: String,
    val weeks: String
)

fun MovieDto.toMovie(): Movie {
    return Movie(
        gross = gross,
        id = id,
        image = image,
        rank = rank,
        title = title,
        weekend = weekend,
        weeks = weeks
    )
}