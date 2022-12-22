package kr.co.younhwan.boxoffice.presentation.movie_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.co.younhwan.boxoffice.BuildConfig
import kr.co.younhwan.boxoffice.common.Constants
import kr.co.younhwan.boxoffice.common.Resource
import kr.co.younhwan.boxoffice.domain.use_case.get_movies.GetMoviesUseCase
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state

    init {
        getMovies()
    }

    private fun getMovies() {
        getMoviesUseCase(apiKey = BuildConfig.API_KEY).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = MovieListState(movies = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = MovieListState(error = result.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }
}