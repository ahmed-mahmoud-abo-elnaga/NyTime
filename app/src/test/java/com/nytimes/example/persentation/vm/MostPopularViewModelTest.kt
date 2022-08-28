package com.nytimes.example.persentation.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nytimes.domain.model.MostPopularDomainModel
import com.nytimes.domain.usecases.GetMostPopular
import com.nytimes.example.persentation.screens.mostpopular.MostPopularUiModel
import com.nytimes.example.persentation.vm.mapper.MostPopularDomainModelToMostPopularUiModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MostPopularViewModelTest {

    private lateinit var MostPopularViewModel: MostPopularViewModel

    @Inject
    private lateinit var getMostPopular: GetMostPopular

    @Inject
    private lateinit var mapper: MostPopularDomainModelToMostPopularUiModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        getMostPopular = mockk()
        mapper = mockk()
        MostPopularViewModel = MostPopularViewModel(getMostPopular, mapper)
    }

    @Test
    fun `When MostPopular Then MostPopularUiModelRetrieved with expected result`() {
        runBlockingTest {
            // Given
            val launchDomainModels = listOf(
                MostPopularDomainModel("1", "", "", ""),
                MostPopularDomainModel("2", "", "", "")
            )
            val expected = listOf(
                MostPopularUiModel("1", "", "", ""),
                MostPopularUiModel("2", "", "", "")
            )
            coEvery { getMostPopular.execute(true) } returns flow {
                emit(launchDomainModels)
            }
            coEvery { mapper.toUiModel(launchDomainModels) } returns expected

            // When
            MostPopularViewModel.MostPopular(true)
            val actualValue = MostPopularViewModel.viewState.value.MostPopularUiModels

            // Then
            coVerify(exactly = 2) { getMostPopular.execute(true) }
            assertEquals(expected, actualValue)
        }
    }

    @Test(expected = Throwable::class)
    fun `Given Error When MostPopular Then expected error state`() {
        runBlockingTest {
            // Given
            var exceptionThrown = false

            // When
            coEvery { getMostPopular.execute(true) } throws java.lang.Exception("Network exception")
            try {
                MostPopularViewModel.MostPopular(true)
            } catch (exception: Exception) {
                exceptionThrown = true
            }
            val actual = MostPopularViewModel.viewState.value.isError

            // Then
            Assert.assertEquals(exceptionThrown, actual)
        }
    }
}