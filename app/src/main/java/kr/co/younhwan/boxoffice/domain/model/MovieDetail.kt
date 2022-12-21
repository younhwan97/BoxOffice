package kr.co.younhwan.boxoffice.domain.model

import kr.co.younhwan.boxoffice.data.remote.dto.*

data class MovieDetail(
    val id: String,
    val title: String,
    val year: String,
    val releaseDate: String,
    val image: String,
    val runtimeStr: String,
    val plot: String,
    val directors: String,
    val writers: String,
    val stars: String,
    val genreList: List<Genre>,
    val imDbRating: String,
    val boxOffice: BoxOffice,
    val keywordList: List<String>,
)