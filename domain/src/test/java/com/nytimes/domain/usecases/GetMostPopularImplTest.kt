package com.nytimes.domain.usecases

import com.nytimes.domain.MostPopularRepository
import com.nytimes.domain.mapper.MostPopularDomainFilter
import com.nytimes.domain.model.MostPopularDomainModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetMostPopularImplTest {
    private lateinit var MostPopularImpl: GetMostPopular

    @Inject
    lateinit var mostPopularRepository: MostPopularRepository

    @Inject
    lateinit var mostPopularDomainFilter: MostPopularDomainFilter


    @Before
    fun setup() {
        mostPopularRepository = mockk()
        mostPopularDomainFilter = mockk()
        MostPopularImpl =
            GetMostPopularImpl(mostPopularDomainFilter, mostPopularRepository)
    }


    @Test
    fun `when execute then return expected MostPopularDomainModels`() {
        runBlocking {
            //Given
            val MostPopularDomain = listOf(
                MostPopularDomainModel("test", "url", "test",""),
                MostPopularDomainModel("test2", "url2", "test2","")
            )

            val expected = listOf(
                MostPopularDomainModel("test", "url", "test",""),
                MostPopularDomainModel("test2", "url2", "test2","")
            )

            coEvery { mostPopularRepository.getMostPopular() } returns flowOf(
                MostPopularDomain
            )
            coEvery {
                mostPopularDomainFilter.order(
                    MostPopularDomain,
                    false
                )
            } returns MostPopularDomain

            // when
            val actualValue = MostPopularImpl.execute(false).first()

            //Verify
            coVerify(exactly = 1) { mostPopularRepository.getMostPopular() }
            assertEquals(expected, actualValue)

        }
    }

}