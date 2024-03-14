package com.nomadiq.chipdogstask.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.model.DogBreedImageDetail
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import com.nomadiq.chipdogstask.domain.usecase.GetDogBreedRandomImageUseCase
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import utils.CoroutineTestRule
import utils.TestConstants.BREED
import utils.TestConstants.UNKNOWN_ERROR

@ExperimentalCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class)
class DogBreedRandomImageViewModelTest {

    private lateinit var viewModel: DogBreedRandomImageViewModel

    private val dataRepository = mockk<DogBreedRepository>()

    private val usecase = mockk<GetDogBreedRandomImageUseCase>()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule() // for the livedata

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule() // to replace the main dispatcher of the view model

    @Before
    fun setUp() {
        viewModel = DogBreedRandomImageViewModel(savedStateHandle = SavedStateHandle())
    }

    @Test
    fun `initialize then fetch dog breeds viewmodel succeeded`() = runTest {
        // given
        val breed = BREED
        val result =
            DogBreedRandomImageResult.Data(
                items =
                listOf(
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_251.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-afghan/n02088094_4396.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-basset/n02088238_13222.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-blood/n02088466_7004.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-blood/n02088466_7800.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-ibizan/n02091244_1541.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-ibizan/n02091244_2464.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-ibizan/n02091244_716.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-walker/n02089867_1430.jpg"),
                    DogBreedImageDetail("https://images.dog.ceo/breeds/hound-walker/n02089867_1790.jpg"),
                )
            )

        // when
        coEvery { dataRepository.fetchRandomImagesByDogBreed(breed = breed) } returns (result)
        coEvery { usecase.invoke(breed = BREED) } returns flow {
            emit(result)

            // then
            viewModel.uiState.value.items.size shouldBe 10
        }
    }

    @Test
    fun `initialize then fetch dog breeds viewmodel failed Empty list`() = runTest {
        // given
        val breed = BREED
        val resultEmptyList =
            DogBreedRandomImageResult.Empty

        // when
        coEvery { dataRepository.fetchRandomImagesByDogBreed(breed = breed) } returns (resultEmptyList)
        coEvery { usecase.invoke(breed = breed) } returns flow {
            emit(resultEmptyList)

            // then
            viewModel.uiState.value.items.size shouldBe 0
        }
    }


    @Test
    fun `initialize then fetch dog breeds viewmodel failed Error`() = runTest {
        // given
        val breed = BREED
        val result =
            DogBreedRandomImageResult.Error(error = UNKNOWN_ERROR)

        // when
        coEvery { dataRepository.fetchRandomImagesByDogBreed(breed = breed) } returns (result)
        coEvery { usecase.invoke(breed = breed) } returns flow {
            emit(result)

            // then
            viewModel.uiState.value.items.size shouldBe 0
        }
    }
}