package com.nomadiq.chipdogstask.data.repository

import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow


/**
 * @author Michael Akakpo
 *
 * This interface enables access to the data entry point [DogBreedRepository] for Dog breeds
 */
interface DogBreedRepository {
    suspend fun fetchAllDogBreeds(): DogBreedListResult
}
