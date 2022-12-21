package kr.co.younhwan.boxoffice.data.remote

import kr.co.younhwan.boxoffice.data.remote.dto.GetBoxOfficeResult
import kr.co.younhwan.boxoffice.data.remote.dto.MovieDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BoxOfficeApi {

    @GET("BoxOffice/{apiKey}")
    suspend fun getBoxOffice(@Path("apiKey") apiKey: String): GetBoxOfficeResult

    @GET("Title/{apiKey}/{movieId}")
    suspend fun getBoxOfficeDetail(@Path("apiKey") apiKey: String, @Path("movieId") movieId: String) : MovieDetailDto
}