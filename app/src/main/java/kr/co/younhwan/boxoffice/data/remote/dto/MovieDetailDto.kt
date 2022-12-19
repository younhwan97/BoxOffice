package kr.co.younhwan.boxoffice.data.remote.dto

import kr.co.younhwan.boxoffice.domain.model.MovieDetail

data class MovieDetailDto(
    val actorList: List<Actor>,
    val awards: String,
    val boxOffice: BoxOffice,
    val companies: String,
    val companyList: List<Company>,
    val contentRating: String,
    val countries: String,
    val countryList: List<Country>,
    val directorList: List<Director>,
    val directors: String,
    val errorMessage: Any,
    val fullCast: Any,
    val fullTitle: String,
    val genreList: List<Genre>,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingVotes: String,
    val image: String,
    val images: Any,
    val keywordList: List<String>,
    val keywords: String,
    val languageList: List<Language>,
    val languages: String,
    val metacriticRating: String,
    val originalTitle: String,
    val plot: String,
    val plotLocal: String,
    val plotLocalIsRtl: Boolean,
    val posters: Any,
    val ratings: Any,
    val releaseDate: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val similars: List<Similar>,
    val starList: List<Star>,
    val stars: String,
    val tagline: Any,
    val title: String,
    val trailer: Any,
    val tvEpisodeInfo: Any,
    val tvSeriesInfo: Any,
    val type: String,
    val wikipedia: Any,
    val writerList: List<Writer>,
    val writers: String,
    val year: String
)

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id, title, year, image, runtimeMins, plot, directorList, writerList, starList, actorList, genreList, countryList, companyList, languageList, contentRating, imDbRating, imDbRatingVotes, boxOffice, keywordList
    )
}