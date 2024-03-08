package com.nomadiq.chipdogstask.domain.mapper

import com.nomadiq.chipdogstask.domain.model.DogBreed

/**
 * @author Michael Akakpo
 *
 *[DogBreedListResult]
 *
 * This will be used to store the result when a list of [DogBreed] is retrieved from the API
 **/

sealed class DogBreedListResult {
    data class Data(
        val dogBreedsList: List<DogBreed>
    ) : DogBreedListResult()

    data class Error(
        val error: String
    ) : DogBreedListResult()

    data object Empty : DogBreedListResult()
    data object NetworkError : DogBreedListResult()
}