package com.nomadiq.chipdogstask.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Michael Akakpo
 *
 * data class for saving root JSON response from the API.
 */

@JsonClass(generateAdapter = true)
data class DogBreedApiResponse(
    @Json(name = "message") val message: Map<String, List<String>>,
    val status: String,
)
