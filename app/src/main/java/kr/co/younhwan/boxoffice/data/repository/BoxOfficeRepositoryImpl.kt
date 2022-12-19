package kr.co.younhwan.boxoffice.data.repository

import kr.co.younhwan.boxoffice.data.remote.BoxOfficeApi
import kr.co.younhwan.boxoffice.data.remote.dto.MovieDetailDto
import kr.co.younhwan.boxoffice.data.remote.dto.MovieDto
import kr.co.younhwan.boxoffice.domain.repository.BoxOfficeRepository
import javax.inject.Inject

class BoxOfficeRepositoryImpl @Inject constructor(
    private val api: BoxOfficeApi
) : BoxOfficeRepository {

    override suspend fun getBoxOffice(apiKey: String): List<MovieDto> {
        return api.getBoxOffice(apiKey)
    }

    override suspend fun getBoxOfficeDetail(apiKey: String, movieId: String): MovieDetailDto {
        return api.getBoxOfficeDetail(apiKey, movieId)
    }
}