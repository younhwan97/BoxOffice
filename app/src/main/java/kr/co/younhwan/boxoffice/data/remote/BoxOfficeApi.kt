package kr.co.younhwan.boxoffice.data.remote

import kr.co.younhwan.boxoffice.data.remote.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BoxOfficeApi {

    @GET("/en/API/BoxOffice/{apiKey}")
    suspend fun getBoxOffice(@Path("apiKey") apiKey: String): List<MovieDto>
}