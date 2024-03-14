package com.nomadiq.chipdogstask.domain.usecase

import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * Use case for loading a list of [@DogBreed] for user to view within a list
 */


class GetDogBreedListUseCase @Inject constructor(
    private val dogBreedRepository: DogBreedRepository,
) {

    suspend operator fun invoke(): Flow<DogBreedListResult> {
        val dogBreedListResult = dogBreedRepository.fetchAllDogBreeds()
        return flow {
            emit(dogBreedListResult)
        }
    }
}

