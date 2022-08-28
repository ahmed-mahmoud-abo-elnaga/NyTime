package com.nytimes.example.persentation.vm.di

import com.nytimes.example.persentation.vm.mapper.MostPopularDomainModelToMostPopularUiModel
import com.nytimes.example.persentation.vm.mapper.MostPopularDomainModelToMostPopularUiModelImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {
    @Provides
    @Reusable
    fun provideMostPopularDomainModelToMostPopularUiModel(
    ): MostPopularDomainModelToMostPopularUiModel =
        MostPopularDomainModelToMostPopularUiModelImpl()

}
