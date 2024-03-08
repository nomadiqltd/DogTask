package com.nomadiq.chipdogstask.data.mapper

import com.nomadiq.chipdogstask.data.api.ResultStatus
import com.nomadiq.chipdogstask.data.model.DogBreedRandomImagesResponse
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.model.DogBreedImageDetail


/**
 * @author Michael Akakpo
 *
 * This Mapper converts the initial [ResultStatus] from the API request into a lightweight domain friendly [DogBreedRandomImageResult]
 * which in turn will be used within the ViewModel to allow a prefixed set of outcomes from the result.
 *
 * This will be used to store the result from when a random image of a particular [DogBreed] is requested from the API
 *
 */
class DogBreedRandomImageListMapper :
    Mapper<ResultStatus<DogBreedRandomImagesResponse>, DogBreedRandomImageResult> {

    override fun map(value: ResultStatus<DogBreedRandomImagesResponse>): DogBreedRandomImageResult {
        return when (value) {
            is ResultStatus.Data -> DogBreedRandomImageResult.Data(
                createDataFromResponseResult(
                    value.result
                )
            )

            is ResultStatus.Empty -> DogBreedRandomImageResult.Empty
            is ResultStatus.Error -> DogBreedRandomImageResult.Error(value.error)
            is ResultStatus.NetworkError -> DogBreedRandomImageResult.NetworkError
        }
    }

    private fun createDataFromResponseResult(data: DogBreedRandomImagesResponse): List<DogBreedImageDetail> {
        val list = mutableListOf<DogBreedImageDetail>()
        data.message?.forEach { item ->
            list.addAll(listOf(DogBreedImageDetail(imageUrl = item)))
        }
        return list
    }
}
