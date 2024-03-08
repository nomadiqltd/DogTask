package com.nomadiq.chipdogstask.domain.repository

import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.model.DogBreed
import kotlinx.coroutines.flow.Flow

/**
 * @author Michael Akakpo
 *
 * This interface enables access to and allows CRUD operations on [DogBreed] being passed through the domain layer (performed by the data layer)
 */
interface DogBreedRepository {
    suspend fun fetchAllDogBreeds(): DogBreedListResult
    suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult
}
