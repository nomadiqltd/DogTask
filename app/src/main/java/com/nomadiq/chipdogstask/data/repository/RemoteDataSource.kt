package com.nomadiq.chipdogstask.data.repository

import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult


/**
 * @author Michael Akakpo
 *
 * This remote data source, fetches data for the [DogBreedRepository]
 * from a remote source which is the dog ceo api.
 *
 */
interface RemoteDataSource {
    suspend fun fetchAllDogBreeds(): DogBreedListResult
}
