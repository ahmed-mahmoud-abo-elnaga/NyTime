package com.nytimes.data.repository

import com.nytimes.data.MostPopularRemoteSource
import com.nytimes.data.mapper.MostPopularRepositoryModelToMostPopularDomainModel
import com.nytimes.domain.MostPopularRepository
import com.nytimes.domain.model.MostPopularDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MostPopularRepositoryImpl @Inject constructor(
    private val mostPopularRemoteSource: MostPopularRemoteSource,
    private val mapper: MostPopularRepositoryModelToMostPopularDomainModel
) : MostPopularRepository {
    override suspend fun getMostPopular(): Flow<List<MostPopularDomainModel>> {
        return mostPopularRemoteSource.getMostPopular().map { lists ->
            mapper.toDomainModel(lists)
        }
    }
}