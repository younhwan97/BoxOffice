package kr.co.younhwan.boxoffice.domain.model

import kr.co.younhwan.boxoffice.data.remote.dto.*

data class MovieDetail(
    val id: String,
    val title: String,
    val year: String,
    val image: String,
    val runtimeMins: String,
    val plot: String,
    val directorList: List<Director>,
    val writerList: List<Writer>,
    val starList: List<Star>,
    val actorList: List<Actor>,
    val genreList: List<Genre>,
    val countryList: List<Country>,
    val companyList: List<Company>,
    val languageList: List<Language>,
    val contentRating: String,
    val imDbRating: String,
    val imDbRatingVotes: String,
    val boxOffice: BoxOffice,
    val keywordList: List<String>,
)