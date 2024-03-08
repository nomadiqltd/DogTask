package com.nomadiq.chipdogstask.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nomadiq.chipdogstask.domain.model.DogBreed
import com.nomadiq.chipdogstask.presentation.ui.component.DogBreedItem
import com.nomadiq.chipdogstask.presentation.ui.theme.ChipDogsTaskTheme
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedListUiState

@Composable
fun DogBreedListScreen(
    onItemClick: (DogBreed) -> Unit = {},
    uiState: DogBreedListUiState,
) {
    ChipDogsTaskTheme {
        val items = uiState.items
        LazyColumn(
            modifier = Modifier
                .background(color = Color.Blue)
                .fillMaxWidth()
                .height(100.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items.forEach { item ->
                item {
                    DogBreedItem(item = item, onItemClick = onItemClick)
                }
            }
        }
    }
}