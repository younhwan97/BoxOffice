package kr.co.younhwan.boxoffice.domain.use_case.get_movies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.younhwan.boxoffice.common.Constants
import kr.co.younhwan.boxoffice.common.Constants.API_KEY
import kr.co.younhwan.boxoffice.common.Resource
import kr.co.younhwan.boxoffice.data.remote.dto.toMovie
import kr.co.younhwan.boxoffice.domain.model.Movie
import kr.co.younhwan.boxoffice.domain.repository.BoxOfficeRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: BoxOfficeRepository
) {

    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getBoxOffice(apiKey = API_KEY).map { it.toMovie() }
            emit(Resource.Success(movies))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}