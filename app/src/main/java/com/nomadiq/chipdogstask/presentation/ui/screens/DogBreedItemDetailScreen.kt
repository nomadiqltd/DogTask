package com.nomadiq.chipdogstask.presentation.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nomadiq.chipdogstask.R
import com.nomadiq.chipdogstask.domain.model.DogBreedImageDetail
import com.nomadiq.chipdogstask.presentation.ui.theme.ChipDogsTaskTheme
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedRandomImageUiState

/**
 * @author - Michael Akakpo
 *
 * The detail screen which houses a [LazyVerticalStaggeredGrid] to display the image content
 */


@ExperimentalAnimationApi
@Composable
fun DogBreedItemDetailScreen(
    navController: NavHostController,
    uiState: DogBreedRandomImageUiState,
) {
    ChipDogsTaskTheme {
        val items = uiState.items

        Scaffold(
            topBar = {
                DogBreedTopAppBar(
                    title = stringResource(id = R.string.toolbar_title_dogbreed_detail),
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ) { paddingValues ->

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
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
