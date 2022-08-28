package com.nytimes.data.mapper

import com.nytimes.data.model.MostPopularRepositoryModel
import com.nytimes.domain.model.MostPopularDomainModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class MostPopularRepositoryModelToMostPopularDomainModelImplTest(
    private val givenMostPopularModel: List<MostPopularRepositoryModel>,
    private val expected: List<MostPopularDomainModel>,
) {
    private lateinit var mapper: MostPopularRepositoryModelToMostPopularDomainModel

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    listOf(MostPopularRepositoryModel("", "", "", "")),
                    listOf(MostPopularDomainModel("", "", "", ""))
                ),
                arrayOf(
                    listOf(MostPopularRepositoryModel("title", "", "", "")),
                    listOf(MostPopularDomainModel("title", "", "", ""))
                ),
                arrayOf(
                    listOf(MostPopularRepositoryModel("", "image", "", "")),
                    listOf(MostPopularDomainModel("", "image", "", ""))
                ),
                arrayOf(
                    listOf(MostPopularRepositoryModel("", "", "filter", "")),
                    listOf(MostPopularDomainModel("", "", "filter", ""))
                )

            )
        }
    }

    @Before
    fun setup() {
        mapper =
            MostPopularRepositoryModelToMostPopularDomainModelImpl()
    }

    @Test
    fun `Given  MostPopularModel when toDomainModel then returns expected result`() {
        // When
        val actualValue =
            mapper.toDomainModel(givenMostPopularModel)

        // Then
        assertEquals(expected, actualValue)
    }

}