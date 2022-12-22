package kr.co.younhwan.boxoffice.presentation.movie_detail

import kr.co.younhwan.boxoffice.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)