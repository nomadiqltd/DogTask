package com.nomadiq.chipdogstask.presentation.viewmodel

import com.nomadiq.chipdogstask.domain.model.DogBreed

data class DogBreedListUiState(
    val items: List<DogBreed>,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)
