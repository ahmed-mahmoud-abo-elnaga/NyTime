package com.nytimes.example.persentation.vm.mapper

import com.nytimes.domain.model.MostPopularDomainModel
import com.nytimes.example.persentation.screens.mostpopular.MostPopularUiModel
import javax.inject.Inject

interface MostPopularDomainModelToMostPopularUiModel {
    fun toUiModel(MostPopular: List<MostPopularDomainModel>): List<MostPopularUiModel>
}

class MostPopularDomainModelToMostPopularUiModelImpl @Inject constructor() :
    MostPopularDomainModelToMostPopularUiModel {
    override fun toUiModel(MostPopular: List<MostPopularDomainModel>): List<MostPopularUiModel> {
        return MostPopular.map { MostPopularDomainModel ->
            MostPopularUiModel(
                title = MostPopularDomainModel.title,
                image = MostPopularDomainModel.image,
                url = MostPopularDomainModel.url,
                subtitle = MostPopularDomainModel.subtitle,
            )
        }
    }

}