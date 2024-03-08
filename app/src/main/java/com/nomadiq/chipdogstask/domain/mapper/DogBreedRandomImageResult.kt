package com.nomadiq.chipdogstask.domain.mapper

import com.nomadiq.chipdogstask.domain.model.DogBreedImageDetail

/**
 * @author Michael Akakpo
 *
 *[DogBreedRandomImageResult]
 *
 * This will be used to store the result from when a random image of a particular [DogBreed] is requested from the API
 **/

sealed class DogBreedRandomImageResult {
    data class Data(
        val items: List<DogBreedImageDetail>
    ) : DogBreedRandomImageResult()

    data class Error(
        val error: String
    ) : DogBreedRandomImageResult()

    data object Empty : DogBreedRandomImageResult()
    data object NetworkError : DogBreedRandomImageResult()
}