package com.nomadiq.chipdogstask.domain.usecase

import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Michael Akakpo
 *
 * Use case for fetching a list of [DogBreedImageDetail] from user selecting a particular breed within presentation layer
 *
 */

class GetDogBreedRandomImageUseCase(
    private var dogBreedRepository: DogBreedRepositoryImpl,
    //TODO() - Offload to dispatcher not on main
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(breed: String): Flow<DogBreedRandomImageResult> {
        val dogBreedRandomImageResult =
            dogBreedRepository.fetchRandomImagesByDogBreed(breed = breed)
        return flow {
            emit(dogBreedRandomImageResult)
        }
    }
}

