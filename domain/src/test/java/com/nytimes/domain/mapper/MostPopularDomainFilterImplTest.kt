package com.nytimes.domain.mapper

import com.nytimes.domain.model.MostPopularDomainModel
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MostPopularDomainFilterImplTest {
    private lateinit var mostPopularDomainFilter: MostPopularDomainFilter

    @Before
    fun setup() {
        mostPopularDomainFilter = MostPopularDomainFilterImpl()
    }

    @Test
    fun `Given MostPopular and filter Ascendant flag when filter then return expected result`() {
        val MostPopular = listOf(
            MostPopularDomainModel("c", "url", "test",""),
            MostPopularDomainModel("b", "url", "test",""),
            MostPopularDomainModel("a", "url", "test"," "),
        )
        val expected = listOf(
            MostPopularDomainModel("a", "url", "test"," "),
            MostPopularDomainModel("b", "url", "test",""),
            MostPopularDomainModel("c", "url", "test",""),
        )
        //when
        val actualValue = mostPopularDomainFilter.order(MostPopular, true)

        // then
        assertEquals(expected, actualValue)
    }

    @Test
    fun `Given MostPopular and filter Descendant flag when filter then return expected result`() {
        val MostPopular = listOf(
            MostPopularDomainModel("a", "url", "test"," "),
            MostPopularDomainModel("c", "url", "test",""),
            MostPopularDomainModel("b", "url", "test",""),
        )
        val expected = listOf(
            MostPopularDomainModel("c", "url", "test",""),
            MostPopularDomainModel("b", "url", "test",""),
            MostPopularDomainModel("a", "url", "test"," "),
        )
        //when
        val actualValue = mostPopularDomainFilter.order(MostPopular, false)

        // then
        assertEquals(expected, actualValue)
    }


}