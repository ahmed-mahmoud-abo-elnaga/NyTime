package com.nytimes.example.persentation.vm.mapper

import com.nytimes.domain.model.MostPopularDomainModel
import com.nytimes.example.persentation.screens.mostpopular.MostPopularUiModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import kotlin.test.assertEquals

@RunWith(Parameterized::class)
class MostPopularDomainModelToMostPopularUiModelImplTest(
    private val givenMostPopularModel: List<MostPopularDomainModel>,
    private val expected: List<MostPopularUiModel>,
) {
    private lateinit var mapper: MostPopularDomainModelToMostPopularUiModel

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                arrayOf(
                    listOf(MostPopularDomainModel("", "", "", "")),
                    listOf(MostPopularUiModel("", "", "", ""))
                ),
                arrayOf(
                    listOf(MostPopularDomainModel("title", "", "", "")),
                    listOf(MostPopularUiModel("title", "", "", ""))
                ),
                arrayOf(
                    listOf(MostPopularDomainModel("", "image", "", "")),
                    listOf(MostPopularUiModel("", "image", "", ""))
                ),
                arrayOf(
                    listOf(MostPopularDomainModel("", "", "filter", "")),
                    listOf(MostPopularUiModel("", "", "filter", ""))
                )

            )
        }
    }

    @Before
    fun setup() {
        mapper =
            MostPopularDomainModelToMostPopularUiModelImpl()
    }

    @Test
    fun `Given  MostPopularModel when toUiModel then returns expected result`() {
        // When
        val actualValue =
            mapper.toUiModel(givenMostPopularModel)

        // Then
        assertEquals(expected, actualValue)
    }

}