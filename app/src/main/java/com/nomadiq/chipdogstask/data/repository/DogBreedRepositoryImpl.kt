package com.nomadiq.chipdogstask.data.repository

import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository

/**
 * @author Michael Akakpo
 *
 * This [DogBreedRepository] utilises the remote data source (and potentially others)
 * to aggregate requested data from the [Dog Api].
 *
 */
class DogBreedRepositoryImpl(private val dogBreedDataSource: DogBreedRemoteDataSourceImpl) :
    DogBreedRepository {

    override suspend fun fetchAllDogBreeds(): DogBreedListResult =
        dogBreedDataSource.fetchAllDogBreeds()

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult =
        dogBreedDataSource.fetchRandomImagesByDogBreed(breed = breed)
}
