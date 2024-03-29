package com.nomadiq.chipdogstask.presentation.viewmodel

import com.nomadiq.chipdogstask.domain.model.DogBreed

/**
 *  @author Michael Akakpo
 *
 *  Representing the uiState of the [DogBreedListViewModel], this can be utilised the relevant @Composable
 *
 */
data class DogBreedListUiState(
    val items: List<DogBreed>,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
)
