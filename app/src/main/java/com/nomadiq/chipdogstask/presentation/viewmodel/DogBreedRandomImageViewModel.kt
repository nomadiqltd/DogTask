package com.nomadiq.chipdogstask.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.chipdogstask.data.repository.DogBreedRemoteDataSourceImpl
import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.data.api.DogBreedApiClient
import com.nomadiq.chipdogstask.domain.mapper.DogBreedRandomImageResult
import com.nomadiq.chipdogstask.domain.model.DogBreed
import com.nomadiq.chipdogstask.domain.model.DogBreedImageDetail
import com.nomadiq.chipdogstask.domain.usecase.GetDogBreedRandomImageUseCase
import com.nomadiq.chipdogstask.presentation.base.DogBreedApplication
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author - Michael Akakpo
 *
 * [DogBreedRandomImageViewModel]
 *
 * Defines UI State information about the details of the items of the list of [DogBreed].
 * This uiState displays the list of random images retrieved from a particular [DogBreed]
 *
 */
@HiltViewModel
class DogBreedRandomImageViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    // Define UIState variable for [DogBreed] data to map to the equivalent UI Screens and components
    private val _uiState = MutableStateFlow(DogBreedRandomImageUiState(listOf()))
    val uiState: StateFlow<DogBreedRandomImageUiState> = _uiState.asStateFlow()

    // TODO() - constant value // TODO: store key
    val breed: String = checkNotNull(savedStateHandle.getStateFlow(key = "breed", initialValue = "")).value

    private val getDogBreedRandomImageUseCase: GetDogBreedRandomImageUseCase by lazy {
        GetDogBreedRandomImageUseCase(
            dogBreedRepository = DogBreedRepositoryImpl(
                dogBreedDataSource = DogBreedRemoteDataSourceImpl(apiClient = DogBreedApiClient.create())
            )
        )
    }

    init {
        displayDogBreedRandomImageList(breed.lowercase())
    }

    // Function to fetch List of [DogBreedImageDetail] - save success response and update uiState
    private fun displayDogBreedRandomImageList(breed: String) {
        viewModelScope.launch {
            getDogBreedRandomImageUseCase.invoke(breed).collect { result ->
                Log.d("displayDogBreedRandomImageList ==> ", breed)
                when (result) {
                    is DogBreedRandomImageResult.Data -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = true,
                                items =
                                result.items.map {
                                    DogBreedImageDetail(
                                        imageUrl = it.imageUrl
                                    )
                                }
                            )
                        }
                        Log.d(
                            "displayDogBreedRandomImageList() ==> ",
                            "${result.items.size}"
                        )
                    }

                    else -> {
                        _uiState.update { currentState -> currentState.copy(isLoading = false) }
                    }
                }
            }
        }
        _uiState.update { currentState -> currentState.copy(isLoading = false) }
    }
}
