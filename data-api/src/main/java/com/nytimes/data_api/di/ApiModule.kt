package com.nytimes.data_api.di


import com.nytimes.data.MostPopularRemoteSource
import com.nytimes.data_api.ApiServices
import com.nytimes.data_api.data.MostPopularRemoteSourceImpl
import com.nytimes.data_api.mapper.MostPopularResponseModelToMostPopularRepositoryModel
import com.nytimes.data_api.mapper.MostPopularResponseModelToMostPopularRepositoryModelImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideMostPopularRemoteSource(
        apiServices: ApiServices,
        mapper: MostPopularResponseModelToMostPopularRepositoryModel,
        @Token token: String

    ): MostPopularRemoteSource =
        MostPopularRemoteSourceImpl(apiServices,token, mapper)

    @Provides
    fun provideMostPopularResponseModelToMostPopularRepositoryModel(
        @BaseUrl baseUrl: String
    ): MostPopularResponseModelToMostPopularRepositoryModel =
        MostPopularResponseModelToMostPopularRepositoryModelImpl(baseUrl)


    @Provides
    @JvmStatic
    internal fun provideApi(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)

    @Provides
    @JvmStatic
    internal fun provideRetrofit(
        httpBuilder: OkHttpClient.Builder,
        retrofitBuilder: Retrofit.Builder
    ): Retrofit = retrofitBuilder
        .client(httpBuilder.build())
        .build()
}