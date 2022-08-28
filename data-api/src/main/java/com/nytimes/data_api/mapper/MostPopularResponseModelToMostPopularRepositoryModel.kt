package com.nytimes.data_api.mapper

import com.nytimes.data.model.MostPopularRepositoryModel
import com.nytimes.data_api.di.BaseUrl
import com.nytimes.data_api.model.Results
import javax.inject.Inject

interface MostPopularResponseModelToMostPopularRepositoryModel {
    fun toRepositoryModel(MostPopularRes: List<Results>): List<MostPopularRepositoryModel>
}

class MostPopularResponseModelToMostPopularRepositoryModelImpl @Inject constructor(
    @BaseUrl val baseUrl: String
) :
    MostPopularResponseModelToMostPopularRepositoryModel {
    override fun toRepositoryModel(MostPopularRes: List<Results>): List<MostPopularRepositoryModel> {
        return MostPopularRes.map { MostPopularResponseModel ->
            MostPopularRepositoryModel(
                title = MostPopularResponseModel.title,
                image = MostPopularResponseModel.media.firstOrNull { media -> media.type.equals("image") }?.metadata?.firstOrNull {
                    it.format.equals(
                        "mediumThreeByTwo210"
                    )
                }?.url,
                subtitle = MostPopularResponseModel.abstract,
                url = MostPopularResponseModel.url
            )
        }
    }

}
