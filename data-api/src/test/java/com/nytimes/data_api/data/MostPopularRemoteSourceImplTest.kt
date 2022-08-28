package com.nytimes.data_api.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nytimes.core_android_test.MainCoroutineRule
import com.nytimes.data_api.ApiServices
import com.nytimes.data_api.mapper.MostPopularResponseModelToMostPopularRepositoryModel
import com.nytimes.data_api.model.MostPopularResponseModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MostPopularRemoteSourceImplTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Inject
    private lateinit var apiServices: ApiServices

    @Inject
    private lateinit var mapper: MostPopularResponseModelToMostPopularRepositoryModel

    private lateinit var MostPopularRemoteSourceImpl: MostPopularRemoteSourceImpl
    private val token =""
    @Before
    fun setup() {
        apiServices = mockk()
        mapper = mockk()
        MostPopularRemoteSourceImpl = MostPopularRemoteSourceImpl(apiServices,token, mapper)
    }

    @Test
    fun `When getMostPopular then apiService invoked`() {
        runBlockingTest {
            coEvery { apiServices.getMostPopular(token) } returns
                MostPopularResponseModel("", "", 1, arrayListOf()
            )
            coEvery { mapper.toRepositoryModel(apiServices.getMostPopular(token).results) } returns listOf()
            MostPopularRemoteSourceImpl.getMostPopular()
            //then
            coVerify(exactly = 1) { apiServices.getMostPopular(token) }
        }
    }

}