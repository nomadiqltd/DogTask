package com.nomadiq.chipdogstask.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.model.DogBreed
import com.nomadiq.chipdogstask.domain.model.DogBreedImageDetail
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedListViewModel
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldBeSameInstanceAs
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.core.ValueClassSupport.boxedValue
import io.mockk.every
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import utils.CoroutineTestRule
import utils.TestConstants.BREED
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 *  @author Michael Akakpo
 *
 *  Unit tests for [DogBreedListRepository]
 *
 */


@OptIn(ExperimentalCoroutinesApi::class)
class DogBreedListRepositoryTest {

    private lateinit var datasource: RemoteDataSource

    private lateinit var dataRepository: DogBreedRepositoryImpl

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        datasource = mockk<FakeRemoteDataSource>()
        dataRepository = DogBreedRepositoryImpl(datasource = datasource)
    }

    @Test
    fun `initialize then fetch dog breeds succeeded`() = runTest {
        // given
        val result =
            DogBreedListResult.Data(
                listOf(
                    DogBreed("affenpinscher"),
                    DogBreed("african"),
                    DogBreed("airedal"),
                )
            )

        // when
        coEvery {
            datasource.fetchAllDogBreeds()
        } returns (result)

        coEvery {
            dataRepository.fetchAllDogBreeds()
        } returns (result)

        // then
        assertThat(this is DogBreedListResult)
    }

    @Test
    fun `initialize then fetch random dog images by breed repository succeeded`() = runTest {
        // given
        val breed = BREED
        val resultListRandomImages =
            DogBreedRandomImageResult.Data(
                listOf(
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_251.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_4396.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-basset/n02088238_13222.jpg"),
                )
            )

        // when
        coEvery {
            datasource.fetchRandomImagesByDogBreed(breed = breed)
        } returns (resultListRandomImages)

        coEvery {
            dataRepository.fetchRandomImagesByDogBreed(breed = breed)
        } returns (resultListRandomImages)

        // then
        assertThat(this is DogBreedListResult)
    }
}
