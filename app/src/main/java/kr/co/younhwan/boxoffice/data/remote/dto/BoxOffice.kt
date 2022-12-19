package kr.co.younhwan.boxoffice.data.remote.dto

data class BoxOffice(
    val budget: String,
    val cumulativeWorldwideGross: String,
    val grossUSA: String,
    val openingWeekendUSA: String
)