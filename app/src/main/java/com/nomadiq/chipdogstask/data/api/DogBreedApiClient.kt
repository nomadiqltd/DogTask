package com.nomadiq.chipdogstask.data.api

import com.nomadiq.chipdogstask.data.model.DogBreedApiResponse
import com.nomadiq.chipdogstask.data.model.DogBreedRandomImagesResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author - Michael Akakpo
 *
 * A public interface that exposes the [Dog Ceo Api] to the client
 *
 */
interface DogBreedApiClient {
    @GET("breed/{breed}/images/random/10")
    suspend fun fetchRandomImagesByDogBreed(@Path("breed") breed: String): Response<DogBreedRandomImagesResponse>

    @GET("breeds/list/all")
    suspend fun fetchAllDogBreeds(): Response<DogBreedApiResponse>

    /**
     *  @logger - Debugging and logging request information
     *  @okHttpClient - Call factory for Http calls throuh Retrofit
     *  @Moshi - The binding between JSON Responses and their respective object counterparts
     *  @Retrofit - Make Http Calls to Dog Ceo Api
     *
     * */
    companion object {
        // TODO - Could hide the URL and any API Keys inside a buildConfigField
        private const val BASE_URL = "https://dog.ceo/api/"

        fun create(): DogBreedApiClient {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val moshi: Moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()
                .create(DogBreedApiClient::class.java)
        }
    }
}
