package com.nomadiq.chipdogstask.domain.model

import com.squareup.moshi.Json

/**
 * @author Michael Akakpo
 *
 * Dog Breed Entity class for [DogBreeds], to be used in domain and presentation layers
 *
 */
data class DogBreed(
    @Json(name = "message") val name : String,
)
