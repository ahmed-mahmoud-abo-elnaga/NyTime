package com.nytimes.data_api.data

import com.nytimes.data.MostPopularRemoteSource
import com.nytimes.data.model.MostPopularRepositoryModel
import com.nytimes.data_api.ApiServices
import com.nytimes.data_api.di.Token
import com.nytimes.data_api.mapper.MostPopularResponseModelToMostPopularRepositoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import java.net.ConnectException
import javax.inject.Inject

class MostPopularRemoteSourceImpl @Inject constructor(
    private val apiServices: ApiServices,
    @Token private val token: String,
    private val mapper: MostPopularResponseModelToMostPopularRepositoryModel
) : MostPopularRemoteSource {
    private val _mostPopularSharedFlow = MutableStateFlow(getInitialStateMostPopularModels())


    private val mostPopularSharedFlow = _mostPopularSharedFlow.asSharedFlow()
    override suspend fun getMostPopular(): Flow<List<MostPopularRepositoryModel>> {
        try {
            mapper.toRepositoryModel(apiServices.getMostPopular(token).results).let {
                _mostPopularSharedFlow.emit(it)
            }
        } catch (connectionException: ConnectException) {
            throw connectionException
        }
        return mostPopularSharedFlow.distinctUntilChanged()

    }

    private fun getInitialStateMostPopularModels(): List<MostPopularRepositoryModel> {
        return listOf()
    }

}