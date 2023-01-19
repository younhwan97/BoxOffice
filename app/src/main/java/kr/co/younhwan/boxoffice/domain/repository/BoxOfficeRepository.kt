package kr.co.younhwan.boxoffice.domain.repository

import kr.co.younhwan.boxoffice.data.remote.dto.MovieDetailDto
import kr.co.younhwan.boxoffice.data.remote.dto.MovieDto

interface BoxOfficeRepository {

    suspend fun getBoxOffice(apiKey: String): List<MovieDto>

    suspend fun getBoxOfficeDetail(apiKey: String, movieId: String): MovieDetailDto
}