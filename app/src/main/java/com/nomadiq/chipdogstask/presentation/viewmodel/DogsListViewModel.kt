package com.nomadiq.chipdogstask.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.chipdogstask.data.mapper.ResultStatus
import com.nomadiq.chipdogstask.data.network.DogApiService
import com.nomadiq.chipdogstask.data.repository.DogBreedRemoteDataSourceImpl
import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.domain.GetDogBreedListUseCase
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class DogsListViewModel : ViewModel() {

    // Define UIState variables for user data to map to UI Screens and components
    private val isLoading = mutableStateOf(false)

    private val dogBreedListUiState: MutableStateFlow<ResultDataUIState<DogBreedListResult>> =
        MutableStateFlow(ResultDataUIState.Loading())

    private val getCalendarUseCase: GetDogBreedListUseCase by lazy {
        GetDogBreedListUseCase(
            dogBreedRepository = DogBreedRepositoryImpl(
                dataSourceImpl = DogBreedRemoteDataSourceImpl(DogApiService)
            )
        )
    }

    init {
        displayDogBreeds()
    }

    // Function to fetch user data
    private fun displayDogBreeds() {

        // Show loading indicator
        isLoading.value = true

        viewModelScope.launch {
            getCalendarUseCase.invoke().collect { dogBreedResultList ->
                when (dogBreedResultList) {
                    is DogBreedListResult.Data -> {
                        dogBreedListUiState.value = ResultDataUIState.Success(dogBreedResultList)
                        Log.d("ViewModel() ==> ", "Success")
                    }

                    else -> {}
                }
            }
        }

        // Hide loading indicator
        isLoading.value = false
    }
}
