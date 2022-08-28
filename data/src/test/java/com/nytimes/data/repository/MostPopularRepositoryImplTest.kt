package com.nytimes.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nytimes.core_android_test.MainCoroutineRule
import com.nytimes.data.MostPopularRemoteSource
import com.nytimes.data.mapper.MostPopularRepositoryModelToMostPopularDomainModel
import com.nytimes.data.model.MostPopularRepositoryModel
import com.nytimes.domain.model.MostPopularDomainModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import javax.inject.Inject
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class MostPopularRepositoryImplTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var MostPopularRepositoryImpl: MostPopularRepositoryImpl

    @Inject
    private lateinit var mostPopularRemoteSource: MostPopularRemoteSource

    @Inject
    private lateinit var mapper: MostPopularRepositoryModelToMostPopularDomainModel


    @Before
    fun setup() {
        mostPopularRemoteSource = mockk()
        mapper = mockk()
        MostPopularRepositoryImpl =
            MostPopularRepositoryImpl(mostPopularRemoteSource, mapper)
    }

    @Test
    fun `When getMostPopular then MostPopularRemoteSource invoked`() {
        runBlockingTest {

            val mostPopularRepositoryModel = MostPopularRepositoryModel("", "", "", "")
            val expected = listOf(MostPopularDomainModel("", "", "", ""))

            // When
            coEvery { mostPopularRemoteSource.getMostPopular() } returns flow {
                emit(
                    listOf(mostPopularRepositoryModel)
                )
            }
            coEvery { mapper.toDomainModel(listOf(mostPopularRepositoryModel)) } returns expected

            val actualMostPopular = MostPopularRepositoryImpl.getMostPopular()

            // Then
            coVerify(exactly = 1) { mostPopularRemoteSource.getMostPopular() }
            actualMostPopular.collect { MostPopular ->
                assertEquals(
                    expected,
                    MostPopular
                )
            }
        }
    }

}