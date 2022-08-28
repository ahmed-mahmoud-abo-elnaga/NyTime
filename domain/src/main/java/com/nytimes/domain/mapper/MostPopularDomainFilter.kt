package com.nytimes.domain.mapper

import com.nytimes.domain.model.MostPopularDomainModel
import javax.inject.Inject

interface MostPopularDomainFilter {
    fun order(
        watchers: List<MostPopularDomainModel>,
        ascendantOrder: Boolean
    ): List<MostPopularDomainModel>
}

class MostPopularDomainFilterImpl @Inject constructor() : MostPopularDomainFilter {
    override fun order(
        watchers: List<MostPopularDomainModel>,
        ascendantOrder: Boolean
    ): List<MostPopularDomainModel> {
        return if (ascendantOrder) watchers.sortedBy { it.title }
        else watchers.sortedByDescending { it.title }
    }

}