package kr.co.younhwan.boxoffice.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kr.co.younhwan.boxoffice.common.Constants
import kr.co.younhwan.boxoffice.common.Resource
import kr.co.younhwan.boxoffice.domain.use_case.get_movie.GetMovieUseCase
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_MOVIE_ID)?.let { movieId ->
            getMovie(movieId)
        }
    }

    private fun getMovie(movieId: String) {
        getMovieUseCase(movieId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = MovieDetailState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = MovieDetailState(movie = result.data)
                }

                is Resource.Error -> {
                    _state.value = MovieDetailState(error = result.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }

}