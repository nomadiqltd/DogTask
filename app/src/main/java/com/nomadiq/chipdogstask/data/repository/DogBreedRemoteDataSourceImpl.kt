package com.nomadiq.chipdogstask.data.repository

import com.nomadiq.chipdogstask.data.network.DogApiService
import android.util.Log
import com.nomadiq.chipdogstask.data.mapper.DogBreedListMapper
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.data.mapper.ResultStatus
import com.nomadiq.chipdogstask.data.model.DogBreedApiResponse
import retrofit2.HttpException
import retrofit2.Response

/**
 * @author Michael Akakpo
 *
 * This is the remote data source, fetching data from a remote source,
 * process the result and allow retrieval of data from the [dog ceo api]
 *
 */
class DogBreedRemoteDataSourceImpl(private val apiClient: DogApiService) : DataSource {

    private val dogBreedListMapper: DogBreedListMapper by lazy {
        DogBreedListMapper()
    }

    override suspend fun fetchAllDogBreeds(): DogBreedListResult {
        val result = fetchAllDogBreedResult()
        return dogBreedListMapper.map(result)
    }

    private suspend fun fetchAllDogBreedResult(): ResultStatus<DogBreedApiResponse> {
        return exceptionHandler {
            val response = apiClient.apiService.getAllDogBreeds()
            buildResultFrom(response)
        }
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


    /*
     * Provide more information on Network related exceptions and propagate to the UI layer
     */
    private suspend fun <T> exceptionHandler(callback: suspend () -> ResultStatus<T>): ResultStatus<T> {
        return try {
            callback.invoke()
        } catch (e: Throwable) {
            when (e) {
                // TODO() - is NetworkConnectivity error case (use Connectivity manager Util)
                is HttpException -> ResultStatus.Error(
                    error =
                    "HttpException ==> ${e.message()}"
                )

                else -> ResultStatus.Error(error = "errorCode ==> ${e.message}")
            }
        }
    }
}
