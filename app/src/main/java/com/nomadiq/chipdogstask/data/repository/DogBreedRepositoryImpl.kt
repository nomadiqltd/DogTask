package com.nomadiq.chipdogstask.data.repository

import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult

/**
 * @author Michael Akakpo
 * This is the remote data source, we are fetching data from remote source in our case, dog ceo api.
 *
 */
class DogBreedRepositoryImpl(private val dataSourceImpl: DogBreedRemoteDataSourceImpl) :
    DogBreedRepository {

    override suspend fun fetchAllDogBreeds(): DogBreedListResult =
        dataSourceImpl.fetchAllDogBreeds()
}
