package com.nomadiq.chipdogstask.domain.mapper

import com.nomadiq.chipdogstask.domain.model.DogBreed

sealed class DogBreedListResult {
    data class Data(
        val cards: List<DogBreed>? = null
    ) : DogBreedListResult()
    data class Error(
        val error: String
    ) : DogBreedListResult()
    data object Loading : DogBreedListResult()
    data object Empty : DogBreedListResult()
    data object NetworkError : DogBreedListResult()
}