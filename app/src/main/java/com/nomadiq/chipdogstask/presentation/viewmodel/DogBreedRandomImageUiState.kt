package com.nomadiq.chipdogstask.presentation.viewmodel

import com.nomadiq.chipdogstask.domain.model.DogBreedImageDetail

data class DogBreedRandomImageUiState(
    val items: List<DogBreedImageDetail>,
    val isLoading: Boolean = true
)
