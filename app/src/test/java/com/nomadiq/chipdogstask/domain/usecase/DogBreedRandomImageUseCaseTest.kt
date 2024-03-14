package com.nomadiq.chipdogstask.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.model.DogBreedImageDetail
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import utils.CoroutineTestRule
import utils.TestConstants.BREED
import kotlin.test.assertTrue

/**
 *  @author Michael Akakpo
 *
 *  Usecase test for determining correct functioning of Usecase and supporting Repository
 *
 */

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DogBreedRandomImageUseCaseTest {

    private val dataRepository = mockk<DogBreedRepository>()

    private lateinit var usecase: GetDogBreedRandomImageUseCase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // for the livedata

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule() // to replace the main dispatcher of the view model


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        usecase = GetDogBreedRandomImageUseCase(dogBreedRepository = dataRepository)
    }

    @Test
    fun `initialize then fetch dog breeds usecase succeeded`() = runTest {
        // given
        val breed = BREED
        val result =
            DogBreedRandomImageResult.Data(
                listOf(
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_251.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_4396.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-basset/n02088238_13222.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-blood/n02088466_7004.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-walker/n02089867_1790.jpg"),
                )
            )

        // when
        coEvery { dataRepository.fetchRandomImagesByDogBreed(breed) } returns (result)
        coEvery { usecase.invoke(breed) } returns flow {
            emit(result)

            // then
            result shouldBe 5
            assertTrue(this is DogBreedRandomImageResult)
        }
    }

    @Test
    fun `initialize then fetch dog breeds usecase failed Empty list`() = runTest {
        // given
        val breed = BREED
        val result = DogBreedRandomImageResult.Empty

        // when
        coEvery { dataRepository.fetchRandomImagesByDogBreed(breed) } returns (result)
        coEvery { usecase.invoke(BREED) } returns flow {
            emit(result)

            // then
            assertThat(this is DogBreedRandomImageResult)
        }
    }

    @Test
    fun `initialize then fetch dog breeds usecase failed Error`() = runTest {
        // given
        val breed = BREED
        val result = DogBreedRandomImageResult.Error("")

        coEvery { dataRepository.fetchRandomImagesByDogBreed(breed) } returns (result)
        coEvery { usecase.invoke(breed) } returns flow {
            emit(result)

            // then
            assertTrue(this is DogBreedRandomImageResult)
        }
    }
}