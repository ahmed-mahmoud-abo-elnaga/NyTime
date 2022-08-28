package com.nytimes.domain

import com.nytimes.domain.model.MostPopularDomainModel
import kotlinx.coroutines.flow.Flow

interface MostPopularRepository {
    suspend fun getMostPopular(): Flow<List<MostPopularDomainModel>>

}