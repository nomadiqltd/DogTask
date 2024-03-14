package com.nomadiq.chipdogstask.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nomadiq.chipdogstask.R
import com.nomadiq.chipdogstask.domain.model.DogBreed
import com.nomadiq.chipdogstask.presentation.ui.component.DogBreedItem
import com.nomadiq.chipdogstask.presentation.ui.theme.ChipDogsTaskTheme
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedListUiState

/**
 *  @author Michael Akakpo
 *
 *  Composable representing the list of [Dog breed] items within the Lazy column
 *
 */

@Composable
fun DogBreedListScreen(
    onItemClick: (DogBreed) -> Unit = {},
    uiState: DogBreedListUiState,
    navController: NavHostController
) {
    ChipDogsTaskTheme {
        Scaffold(
            topBar = {
                DogBreedTopAppBar(
                    title = stringResource(id = R.string.toolbar_title_dogbreed_list),
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() }
                )
            }
        ) { paddingValues ->

            val items = uiState.items
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items.forEach { item ->
                    item {
                        DogBreedItem(item = item, onItemClick = onItemClick)
                    }
                }
            }
        }
    }
}