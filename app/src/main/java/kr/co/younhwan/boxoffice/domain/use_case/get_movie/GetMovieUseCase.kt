package kr.co.younhwan.boxoffice.domain.use_case.get_movie

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.co.younhwan.boxoffice.common.Constants.API_KEY
import kr.co.younhwan.boxoffice.common.Resource
import kr.co.younhwan.boxoffice.data.remote.dto.toMovieDetail
import kr.co.younhwan.boxoffice.domain.model.MovieDetail
import kr.co.younhwan.boxoffice.domain.repository.BoxOfficeRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: BoxOfficeRepository
) {

    operator fun invoke(movieId: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getBoxOfficeDetail(API_KEY, movieId).toMovieDetail()
            emit(Resource.Success(movieDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}