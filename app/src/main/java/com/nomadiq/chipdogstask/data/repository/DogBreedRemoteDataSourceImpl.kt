package com.nomadiq.chipdogstask.data.repository

import com.nomadiq.chipdogstask.data.network.DogApiService
import com.nomadiq.chipdogstask.data.mapper.DogBreedListMapper
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.data.mapper.ResultStatus
import com.nomadiq.chipdogstask.data.model.DogBreedApiResponse
import retrofit2.Response

/**
 * @author Michael Akakpo
 *
 * This is the remote data source, fetching data from a remote source,
 * process the result and allow retrieval of data from the [dog ceo api]
 *
 */
class DogBreedRemoteDataSourceImpl(private val apiClient: DogApiService) : RemoteDataSource {

    private val dogBreedListMapper: DogBreedListMapper by lazy {
        DogBreedListMapper()
    }

    override suspend fun fetchAllDogBreeds(): DogBreedListResult {
        val result = fetchAllDogBreedResult()
        return dogBreedListMapper.map(result)
    }

    private suspend fun fetchAllDogBreedResult(): ResultStatus<DogBreedApiResponse> {
        val response = apiClient.apiService.getAllDogBreeds()
        return buildResultFrom(response)
    }


    /*
     * Check the response from the [Dog API] and return a [ResultStatus]
     * to provide a predetermined set of possible outcomes
     */
    private fun <T> buildResultFrom(response: Response<T>): ResultStatus<T> {
        if (response.isSuccessful) {
            response.body()?.let {
                return ResultStatus.Data(it)
            } ?: return ResultStatus.Empty(response.code())
        } else {
            return ResultStatus.Error(
                error = "errorCode :: ${response.code()} ==> ${response.message()}"
            )
        }
    }
}
