package com.nomadiq.chipdogstask.data.mapper

import com.nomadiq.chipdogstask.domain.model.DogBreed
import com.nomadiq.chipdogstask.data.model.DogBreedApiResponse
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult

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
        val flattenedListed = data.message.flatMap {
            it.value
        }
        flattenedListed.forEach { item ->
            list.addAll(listOf(DogBreed(name = item)))
        }
        return list
    }
}
