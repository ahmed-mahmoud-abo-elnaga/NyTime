package com.nytimes.domain.di


import com.nytimes.domain.MostPopularRepository
import com.nytimes.domain.mapper.MostPopularDomainFilter
import com.nytimes.domain.mapper.MostPopularDomainFilterImpl
import com.nytimes.domain.usecases.GetMostPopular
import com.nytimes.domain.usecases.GetMostPopularImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Reusable
    fun provideGetMostPopular(
        mostPopularRepository: MostPopularRepository,
        filter: MostPopularDomainFilter
    ): GetMostPopular = GetMostPopularImpl(filter, mostPopularRepository)

    @Provides
    @Reusable
    fun provideMostPopularFilter(): MostPopularDomainFilter =
        MostPopularDomainFilterImpl()


}