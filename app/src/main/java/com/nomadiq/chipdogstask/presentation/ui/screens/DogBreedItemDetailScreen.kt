package com.nomadiq.chipdogstask.presentation.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nomadiq.chipdogstask.domain.model.DogBreedImageDetail
import com.nomadiq.chipdogstask.presentation.ui.theme.ChipDogsTaskTheme
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedListUiState
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedRandomImageUiState
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedRandomImageViewModel

/**
 * As these callbacks are passed in through multiple Composables, to avoid having to name
 * parameters to not mix them up, they're aggregated in this class.
 */
data class PlantDetailsCallbacks(
    val onBackClick: () -> Unit,
)

@ExperimentalAnimationApi
@Composable
fun DogBreedItemDetailScreen(
    navController: NavHostController,
   // onBackClick: () -> Unit,
   // callbacks: PlantDetailsCallbacks,
    uiState: DogBreedRandomImageUiState,
) {
    ChipDogsTaskTheme {
        val items = uiState.items

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalItemSpacing = 8.dp,
            content = {
                items(items) { item ->
                    DogBreedRandomImage(item = item)
                }
            }
        )
    }
}

@Composable
fun DogBreedRandomImage(item: DogBreedImageDetail) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = item.imageUrl, contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )
    }
}
