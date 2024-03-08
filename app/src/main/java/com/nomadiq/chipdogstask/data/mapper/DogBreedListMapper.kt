package com.nomadiq.chipdogstask.data.mapper

import com.nomadiq.chipdogstask.data.api.ResultStatus
import com.nomadiq.chipdogstask.domain.model.DogBreed
import com.nomadiq.chipdogstask.data.model.DogBreedApiResponse
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult


/**
 * @author Michael Akakpo
 *
 * This Mapper converts the initial [ResultStatus] from the API request into a lightweight domain friendly [DogBreedListResult]
 * which in turn will be used within the ViewModel to allow a prefixed set of outcomes from the result.
 *
 */

class DogBreedListMapper : Mapper<ResultStatus<DogBreedApiResponse>, DogBreedListResult> {

    override fun map(value: ResultStatus<DogBreedApiResponse>): DogBreedListResult {
        return when (value) {
            is ResultStatus.Data -> DogBreedListResult.Data(createDataFromResponseResult(value.result))
            is ResultStatus.Empty -> DogBreedListResult.Empty
            is ResultStatus.Error -> DogBreedListResult.Error(value.error)
            is ResultStatus.NetworkError -> DogBreedListResult.NetworkError
        }
    }

    private fun createDataFromResponseResult(data: DogBreedApiResponse): List<DogBreed> {
        val list = mutableListOf<DogBreed>()
        val results = data.message
        // TODO() - Deal with subbreeds and take Breed only (keys) for now as Subbreeds return 404 against random images endpoint
        results.forEach { item ->
            list.addAll(listOf(DogBreed(name = item.key)))
        }
        return list
    }
}
