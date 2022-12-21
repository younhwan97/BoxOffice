package kr.co.younhwan.boxoffice.presentation

sealed class Screen(val route: String) {
    object MovieListScreen: Screen("Movie_list_screen")
    object MovieDetailScreen: Screen("Movie_detail_screen")
}