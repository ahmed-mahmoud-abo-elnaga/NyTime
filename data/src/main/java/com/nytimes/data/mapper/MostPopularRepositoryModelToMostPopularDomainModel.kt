package com.nytimes.data.mapper

import com.nytimes.data.model.MostPopularRepositoryModel
import com.nytimes.domain.model.MostPopularDomainModel
import javax.inject.Inject

interface MostPopularRepositoryModelToMostPopularDomainModel {
    fun toDomainModel(MostPopularRepo: List<MostPopularRepositoryModel>): List<MostPopularDomainModel>
}

class MostPopularRepositoryModelToMostPopularDomainModelImpl @Inject constructor() :
    MostPopularRepositoryModelToMostPopularDomainModel {
    override fun toDomainModel(MostPopularRepo: List<MostPopularRepositoryModel>): List<MostPopularDomainModel> {
        return MostPopularRepo.map { MostPopularRepositoryModel ->
            MostPopularDomainModel(
                title = MostPopularRepositoryModel.title,
                image = MostPopularRepositoryModel.image,
                url = MostPopularRepositoryModel.url,
                subtitle = MostPopularRepositoryModel.subtitle
            )
        }
    }

}
