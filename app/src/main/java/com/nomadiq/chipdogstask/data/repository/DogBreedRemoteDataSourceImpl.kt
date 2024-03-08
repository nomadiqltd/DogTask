package com.nomadiq.chipdogstask.data.repository

import com.nomadiq.chipdogstask.data.mapper.DogBreedListMapper
import com.nomadiq.chipdogstask.data.mapper.DogBreedRandomImageListMapper
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.data.api.ResultStatus
import com.nomadiq.chipdogstask.data.model.DogBreedApiResponse
import com.nomadiq.chipdogstask.data.model.DogBreedRandomImagesResponse
import com.nomadiq.chipdogstask.data.api.DogBreedApiClient
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult

/**
 * @author Michael Akakpo
 *
 * This data source fetches data remotely,
 * processes the result and allows retrieval and mapping of the API data from the [dog ceo api]
 *
 */
class DogBreedRemoteDataSourceImpl(private val apiClient: DogBreedApiClient) : RemoteDataSource {

    private val dogBreedListMapper: DogBreedListMapper by lazy {
        DogBreedListMapper()
    }

    private val dogRandomImageListMapper: DogBreedRandomImageListMapper by lazy {
        DogBreedRandomImageListMapper()
    }

    override suspend fun fetchAllDogBreeds(): DogBreedListResult {
        val result = fetchAllDogBreedResult()
        return dogBreedListMapper.map(result)
    }

    override suspend fun fetchRandomImagesByDogBreed(breed: String): DogBreedRandomImageResult {
        val result = fetchRandomImagesByDogBreedResult(breed = breed)
        return dogRandomImageListMapper.map(result)
    }

    private suspend fun fetchAllDogBreedResult(): ResultStatus<DogBreedApiResponse> {
        val response = apiClient.fetchAllDogBreeds()
        return buildResultStatusFrom(response)
    }

    private suspend fun fetchRandomImagesByDogBreedResult(breed: String): ResultStatus<DogBreedRandomImagesResponse> {
        val response = apiClient.fetchRandomImagesByDogBreed(breed = breed)
        return buildResultStatusFrom(response)
    }
}
