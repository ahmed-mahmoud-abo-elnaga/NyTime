package com.nytimes.data.di

import com.nytimes.data.MostPopularRemoteSource
import com.nytimes.data.mapper.MostPopularRepositoryModelToMostPopularDomainModel
import com.nytimes.data.mapper.MostPopularRepositoryModelToMostPopularDomainModelImpl
import com.nytimes.data.repository.MostPopularRepositoryImpl
import com.nytimes.domain.MostPopularRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MostPopularRepositoryModule {

    @Provides
    @Reusable
    fun provideMostPopularRepository(
        mostPopularRemoteSource: MostPopularRemoteSource,
        mapper: MostPopularRepositoryModelToMostPopularDomainModel
    ): MostPopularRepository =
        MostPopularRepositoryImpl(mostPopularRemoteSource, mapper)

    @Provides
    @Reusable
    fun provideMostPopularRepositoryModelToMostPopularDomainModel():
            MostPopularRepositoryModelToMostPopularDomainModel =
        MostPopularRepositoryModelToMostPopularDomainModelImpl()
}