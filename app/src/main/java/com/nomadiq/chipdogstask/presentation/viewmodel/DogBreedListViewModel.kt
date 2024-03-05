package com.nomadiq.chipdogstask.presentation.viewmodel

import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nomadiq.chipdogstask.data.network.DogApiService
import com.nomadiq.chipdogstask.data.repository.DogBreedRemoteDataSourceImpl
import com.nomadiq.chipdogstask.data.repository.DogBreedRepositoryImpl
import com.nomadiq.chipdogstask.domain.GetDogBreedListUseCase
import com.nomadiq.chipdogstask.domain.mapper.DogBreedListResult
import com.nomadiq.chipdogstask.domain.model.DogBreed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale

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

    private val getCalendarUseCase: GetDogBreedListUseCase by lazy {
        GetDogBreedListUseCase(
            dogBreedRepository = DogBreedRepositoryImpl(
                dataSourceImpl = DogBreedRemoteDataSourceImpl(DogApiService)
            )
        )
    }

    init {
        displayDogBreedList()
    }

    // Function to fetch List of [DogBreed] - save success response and update uiState
    private fun displayDogBreedList() {
        viewModelScope.launch {
            getCalendarUseCase.invoke().collect { result ->
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
                    else -> {
                        _uiState.update { currentState -> currentState.copy(isLoading = false) }
                    }
                }
            }
            _uiState.update { currentState -> currentState.copy(isLoading = false) }
        }
    }
}
