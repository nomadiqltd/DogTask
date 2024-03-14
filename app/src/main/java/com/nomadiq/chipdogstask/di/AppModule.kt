package com.nomadiq.chipdogstask.di

import androidx.lifecycle.SavedStateHandle
import com.nomadiq.chipdogstask.data.api.DogBreedApiClient
import com.nomadiq.chipdogstask.data.repository.DogBreedRemoteDataSourceImpl
import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.data.repository.RemoteDataSource
import com.nomadiq.chipdogstask.domain.repository.DogBreedRepository
import com.nomadiq.chipdogstask.domain.usecase.GetDogBreedListUseCase
import com.nomadiq.chipdogstask.domain.usecase.GetDogBreedRandomImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
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

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideDogBreedRepository(dogBreedDataSource: RemoteDataSource): DogBreedRepository =
        DogBreedRepositoryImpl(dogBreedDataSource)

    @Provides
    fun provideGetDogBreedListUseCase(): GetDogBreedListUseCase = GetDogBreedListUseCase(
        dogBreedRepository = provideDogBreedRepository(
            dogBreedDataSource = provideDogBreedRemoteDataSource(apiClient = provideDogBreedApiClient())
        )
    )

    @Provides
    fun provideGetDogBreedRandomImageUseCase(): GetDogBreedRandomImageUseCase = GetDogBreedRandomImageUseCase(
        dogBreedRepository = provideDogBreedRepository(
            dogBreedDataSource = provideDogBreedRemoteDataSource(apiClient = provideDogBreedApiClient())
        )
    )
}