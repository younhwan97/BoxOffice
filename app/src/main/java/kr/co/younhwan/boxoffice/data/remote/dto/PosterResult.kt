package kr.co.younhwan.boxoffice.data.remote.dto

data class PosterResult(
    val backdrops: List<Backdrop>,
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val posters: List<Poster>,
    val title: String,
    val type: String,
    val year: String
)