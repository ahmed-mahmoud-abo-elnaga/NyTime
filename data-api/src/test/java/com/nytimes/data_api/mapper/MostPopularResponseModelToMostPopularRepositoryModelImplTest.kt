package com.nytimes.data_api.mapper

import com.nytimes.data.model.MostPopularRepositoryModel
import com.nytimes.data_api.model.Results
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class MostPopularResponseModelToMostPopularRepositoryModelImplTest(
    private val givenMostPopularModel: List<Results>,
    private val expected: List<MostPopularRepositoryModel>,
) {
    private lateinit var mapper: MostPopularResponseModelToMostPopularRepositoryModel

    companion object {
        private val baseUrl = "base"

        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    listOf(
                        Results(title = "", abstract = "", url = "")
                    ),
                    listOf(MostPopularRepositoryModel("",  "", null,""))
                )
            )
        }
    }

    @Before
    fun setup() {
        mapper =
            MostPopularResponseModelToMostPopularRepositoryModelImpl(baseUrl)
    }

    @Test
    fun `Given  MostPopularModel when toRepositoryModel then returns expected result`() {
        // When
        val actualValue =
            mapper.toRepositoryModel(givenMostPopularModel)

        // Then
        assertEquals(expected, actualValue)
    }

}