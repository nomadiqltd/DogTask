package com.nomadiq.chipdogstask.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.model.DogBreed
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import utils.CoroutineTestRule
import kotlin.test.assertTrue

/**
 *  @author Michael Akakpo
 *
 *  Usecase test for determining correct functioning of Usecase and supporting Repository
 *
 */


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class DogBreedListUseCaseTest {

    companion object {
        private const val UNKNOWN_ERROR = "Unknown Error occurred"
    }

    private val dataRepository = mockk<DogBreedRepository>()

    private lateinit var usecase: GetDogBreedListUseCase

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule =
        CoroutineTestRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        usecase = GetDogBreedListUseCase(dogBreedRepository = dataRepository)
    }

    @Test
    fun `initialize then fetch dog breeds usecase succeeded`() = runTest {
        // given
        val result =
            DogBreedListResult.Data(
                listOf(
                    DogBreed("affenpinscher"),
                    DogBreed("african"),
                    DogBreed("airedal"),
                    DogBreed("akita"),
                    DogBreed("akita"),
                    DogBreed("akita"),
                )
            )


        // when
        coEvery { dataRepository.fetchAllDogBreeds() } returns (result)
        coEvery { usecase.invoke() } returns flow {
            emit(result)

            // then
            result shouldBe 6
            assertTrue(this is DogBreedListResult)
        }
    }

    @Test
    fun `initialize then fetch dog breeds usecase failed Empty list`() = runTest {
        // given
        coEvery { dataRepository.fetchAllDogBreeds() }.returns(DogBreedListResult.Empty)
        coEvery {
            usecase.invoke().first()
        } returns DogBreedListResult.Empty

        // then
        assertThat(this is DogBreedListResult)
    }

    @Test
    fun `initialize then fetch dog breeds usecase failed Error`() = runTest {
        // given
        coEvery { dataRepository.fetchAllDogBreeds() }.returns(DogBreedListResult.Error(error = UNKNOWN_ERROR))
        coEvery { usecase.invoke() } returns flow {
            emit(DogBreedListResult.Error(error = UNKNOWN_ERROR))
        }

        // then
        assertThat(this is DogBreedListResult)
    }
}