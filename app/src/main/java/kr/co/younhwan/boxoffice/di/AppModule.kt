package kr.co.younhwan.boxoffice.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.co.younhwan.boxoffice.common.Constants
import kr.co.younhwan.boxoffice.data.remote.BoxOfficeApi
import kr.co.younhwan.boxoffice.data.repository.BoxOfficeRepositoryImpl
import kr.co.younhwan.boxoffice.domain.repository.BoxOfficeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBoxOfficeApi(): BoxOfficeApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BoxOfficeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBoxOfficeRepository(api: BoxOfficeApi): BoxOfficeRepository {
        return BoxOfficeRepositoryImpl(api)
    }
}