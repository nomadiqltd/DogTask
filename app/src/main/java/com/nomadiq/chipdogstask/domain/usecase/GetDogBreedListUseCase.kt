package com.nomadiq.chipdogstask.domain.usecase

import androidx.compose.foundation.isSystemInDarkTheme
import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.model.DogBreed
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

/**
 * @author Michael Akakpo
 *
 * Use case for loading a list of [DogBreed] for user to view within a list
 */

class GetDogBreedListUseCase(
    private var dogBreedRepository: DogBreedRepositoryImpl,
    //TODO() - Offload to dispatcher not on main
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(): Flow<DogBreedListResult> {
        val dogBreedListResult = dogBreedRepository.fetchAllDogBreeds()
        return flow {
            emit(dogBreedListResult)
        }
    }
}

