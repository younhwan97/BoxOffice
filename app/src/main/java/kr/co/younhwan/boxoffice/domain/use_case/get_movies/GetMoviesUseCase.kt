package kr.co.younhwan.boxoffice.domain.use_case.get_movies

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
    // Use case 의 경우 Data Layer 에 대한 의존성을 가지면 안된다.
    // 하지만 Use case 는 repository 를 사용하는데 어떻게 의존성을 가지지 않을까?
    // -> repository 를 직접 사용하지 않고 인터페이스를 사용하면서, 의존성을 회피한다.

    // Use case 의 경우 1개의 기능만을 갖는다.
    // 때문에 invoke 함수에서 해당 로직을 구현한다.
    operator fun invoke(apiKey: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getBoxOffice(apiKey = apiKey).map { it.toMovie() }
            emit(Resource.Success(data = movies))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An expected error!"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server"))
        }
    }
}