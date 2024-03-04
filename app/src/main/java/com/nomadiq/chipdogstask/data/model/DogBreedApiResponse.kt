package com.nomadiq.chipdogstask.data.model

import com.squareup.moshi.JsonClass

/**
 * @author Michael Akakpo
 *
 * data class for saving JSON response from the API.
 */

@JsonClass(generateAdapter = true)
data class DogBreedApiResponse(
    val message: Map<String, List<String>>,
    val status: String,
)
