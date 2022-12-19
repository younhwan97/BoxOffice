package kr.co.younhwan.boxoffice.presentation.movie_list

import kr.co.younhwan.boxoffice.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = ""
)