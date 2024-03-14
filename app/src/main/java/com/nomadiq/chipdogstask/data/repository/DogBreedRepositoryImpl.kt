package com.nomadiq.chipdogstask.data.repository

import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import javax.inject.Inject

/**
 * @author Michael Akakpo
 *
 * This [DogBreedRepository] utilises the remote data source (and potentially others)
 * to aggregate requested data from the [Dog Api].
 *
 */
class DogBreedRepositoryImpl @Inject constructor(
    private val datasource: RemoteDataSource,
) :
    DogBreedRepository {

    override suspend fun fetchAllDogBreeds(): DogBreedListResult =
        datasource.fetchAllDogBreeds()

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult =
        datasource.fetchRandomImagesByDogBreed(breed = breed)
}
