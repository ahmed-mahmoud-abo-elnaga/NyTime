package com.nytimes.example.di

import com.nytimes.domain.usecases.GetMostPopular
import com.nytimes.example.persentation.vm.MostPopularViewModel
import com.nytimes.example.persentation.vm.mapper.MostPopularDomainModelToMostPopularUiModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class ViewModelsModule {

    @Provides
    fun provideMostPopularViewModel(
        getMostPopular: GetMostPopular,
        MostPopularDomainModelToMostPopularUiModel: MostPopularDomainModelToMostPopularUiModel
    ) = MostPopularViewModel(
        getMostPopular,
        MostPopularDomainModelToMostPopularUiModel
    )
}
