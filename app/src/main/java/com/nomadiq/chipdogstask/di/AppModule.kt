package com.nomadiq.chipdogstask.di

import androidx.lifecycle.SavedStateHandle
import com.nomadiq.chipdogstask.data.api.DogBreedApiClient
import com.nomadiq.chipdogstask.data.repository.DogBreedRemoteDataSourceImpl
import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.data.repository.RemoteDataSource
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDogBreedApiClient(): DogBreedApiClient {
        return DogBreedApiClient.create()
    }

    @Provides
    fun provideSavedStateHandle(): SavedStateHandle = SavedStateHandle()

    @Singleton
    @Provides
    fun provideDogBreedRemoteDataSource(apiClient: DogBreedApiClient): RemoteDataSource =
        DogBreedRemoteDataSourceImpl(apiClient)

    @Singleton
    @Provides
    fun provideDogBreedRepository(dogBreedDataSource: DogBreedRemoteDataSourceImpl): DogBreedRepository =
        DogBreedRepositoryImpl(dogBreedDataSource)
}