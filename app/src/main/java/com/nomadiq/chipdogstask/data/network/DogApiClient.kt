package com.nomadiq.chipdogstask.data.network

import com.nomadiq.chipdogstask.data.service.DogApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// TODO() - Convert to buildConfigField
private const val BASE_URL = "https://dog.ceo/api/"

/**
 * Build the Moshi object that Retrofit will be using, add Kotlin adapter for
 * Kotlin compatibility.
 */
private val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public Api object that exposes the lazy-initialized API Client service
 */
object DogApiService {
    val apiService: DogApiService by lazy {
        retrofit.create(DogApiService::class.java)
    }
}