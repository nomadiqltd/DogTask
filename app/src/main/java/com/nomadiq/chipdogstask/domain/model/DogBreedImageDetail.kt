package com.nomadiq.chipdogstask.domain.model

import com.squareup.moshi.Json

/**
 * @author Michael Akakpo
 *
 * Dog Breed Entity class for DogBreed images requested once an item in the list of [DogBreed] is selected (to be used in domain and presentation layers)
 *
 */
data class DogBreedImageDetail(
    @Json(name = "message") val imageUrl: String,
)
