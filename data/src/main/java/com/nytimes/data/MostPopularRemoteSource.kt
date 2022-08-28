package com.nytimes.data

import com.nytimes.data.model.MostPopularRepositoryModel
import kotlinx.coroutines.flow.Flow

interface MostPopularRemoteSource {
    suspend fun getMostPopular(): Flow<List<MostPopularRepositoryModel>>

}