package com.nomadiq.chipdogstask.data.service

import com.nomadiq.chipdogstask.data.model.DogBreedApiResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author - Michael Akakpo
 *
 * A public interface that exposes the [Dog Ceo Api] to the api client
 *
 */
interface DogApiService {
    @GET("breeds/image/random/10")
    suspend fun getRandomDogImage()

    @GET("breeds/list/all")
    suspend fun getAllDogBreeds(): Response<DogBreedApiResponse>
}
