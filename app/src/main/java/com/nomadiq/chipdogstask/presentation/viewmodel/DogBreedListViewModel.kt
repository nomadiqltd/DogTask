package com.nomadiq.chipdogstask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.chipdogstask.data.repository.DogBreedRemoteDataSourceImpl
import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.data.api.DogBreedApiClient
import com.nomadiq.chipdogstask.domain.usecase.GetDogBreedListUseCase
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.model.DogBreed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

/**
 * @author - Michael Akakpo
 *
 * [DogBreedListViewModel]
 *
 * Defines UI State information about the list of [DogBreed]
 * retrieved from the API and make it accessible to Compose and other UI components
 *
 */

class DogBreedListViewModel : ViewModel() {

    // Define UIState variable for [DogBreed] data to map to the equivalent UI Screens and components
    private val _uiState = MutableStateFlow(DogBreedListUiState(listOf()))
    val uiState: StateFlow<DogBreedListUiState> = _uiState.asStateFlow()

    @delegate:Inject
    private val getDogBreedListUseCase: GetDogBreedListUseCase by lazy {
        GetDogBreedListUseCase(
            dogBreedRepository = DogBreedRepositoryImpl(
                datasource = DogBreedRemoteDataSourceImpl(apiClient = DogBreedApiClient.create()),
            )
        )
    }

    init {
        displayDogBreedList()
    }

    // Function to fetch List of [DogBreed] - save success response and update uiState
    private fun displayDogBreedList() {
        viewModelScope.launch {
            getDogBreedListUseCase.invoke().collect { result ->
                when (result) {
                    is DogBreedListResult.Data -> {
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = true,
                                items =
                                result.dogBreedsList.map {
                                    DogBreed(
                                        name = it.name.capitalize(Locale.ROOT)
                                    )
                                }
                            )
                        }
                    }

                    is DogBreedListResult.Empty ->
                        logDogBreedListResult("Empty list returned")
                    // TODO - constants for error messages

                    is DogBreedListResult.Error ->
                        logDogBreedListResult("Unknown error occurred")

                    is DogBreedListResult.NetworkError ->
                        logDogBreedListResult("Network error occurred")

                }
                _uiState.update { currentState -> currentState.copy(isLoading = false) }
            }
        }
    }

    private fun logDogBreedListResult(errorMessage: String) {
        _uiState.update {
            it.copy(
                isLoading = false,
                items = listOf(),
                errorMessage = errorMessage
            )
        }
    }
}
