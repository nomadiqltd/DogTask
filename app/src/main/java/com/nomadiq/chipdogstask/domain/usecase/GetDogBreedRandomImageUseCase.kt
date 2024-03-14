package com.nomadiq.chipdogstask.domain.usecase

import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * Use case for fetching a list of [@DogBreedImageDetail] from user selecting a particular breed within presentation layer
 *
 */

class GetDogBreedRandomImageUseCase @Inject constructor(
    private val dogBreedRepository: DogBreedRepository,
) {

    suspend operator fun invoke(breed: String): Flow<DogBreedRandomImageResult> {
        val dogBreedRandomImageResult =
            dogBreedRepository.fetchRandomImagesByDogBreed(breed = breed)
        return flow {
            emit(dogBreedRandomImageResult)
        }
    }
}

