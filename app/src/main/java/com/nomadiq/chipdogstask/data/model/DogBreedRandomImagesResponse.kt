package com.nomadiq.chipdogstask.data.model

/**
 * @author Michael Akakpo
 *
 * data class for saving root JSON response from the Random Images endpoint.
 */
data class DogBreedRandomImagesResponse(
    val message: List<String> = listOf(),
    val status: String = ""
)