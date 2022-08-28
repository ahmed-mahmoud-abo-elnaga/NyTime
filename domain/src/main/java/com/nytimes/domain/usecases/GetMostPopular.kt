package com.nytimes.domain.usecases

import com.nytimes.domain.MostPopularRepository
import com.nytimes.domain.mapper.MostPopularDomainFilter
import com.nytimes.domain.model.MostPopularDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetMostPopular {
    suspend fun execute(ascendantOrder: Boolean): Flow<List<MostPopularDomainModel>>

}

class GetMostPopularImpl @Inject constructor(
    private val mostPopularDomainFilter: MostPopularDomainFilter,
    private val mostPopularRepository: MostPopularRepository
) : GetMostPopular {
    override suspend fun execute(ascendantOrder: Boolean): Flow<List<MostPopularDomainModel>> {
        return mostPopularRepository.getMostPopular().map {
            mostPopularDomainFilter.order(it, ascendantOrder)
        }
    }

}
