package kr.co.younhwan.boxoffice.data.remote.dto

data class GetBoxOfficeResult(
    val errorMessage: String,
    val items: List<MovieDto>
)