package com.nomadiq.chipdogstask.presentation.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nomadiq.chipdogstask.R
import com.nomadiq.chipdogstask.presentation.ui.screens.DogBreedItemDetailScreen
import com.nomadiq.chipdogstask.presentation.ui.screens.DogBreedListScreen
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedListViewModel
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedRandomImageViewModel

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogBreedTopAppBar(
    currentScreen: ScreenDestination,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.back_button)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun DogBreedsApp(
    viewModel: DogBreedListViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
//    val currentScreen = ScreenDestination.valueOf(
//        backStackEntry?.destination?.route ?: ScreenDestination.DogBreedDetailScreen.name
//    )

    Scaffold(
        topBar = {
            DogBreedTopAppBar(
                currentScreen = ScreenDestination.DogBreedListScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            startDestination = ScreenDestination.DogBreedListScreen.route
        ) {
            composable(route = ScreenDestination.DogBreedListScreen.route) {
                val uiState by viewModel.uiState.collectAsState()
                DogBreedListScreen(
                    uiState = uiState,
                    onItemClick = {
                        navController.navigate(
                            ScreenDestination.DogBreedDetailScreen.createRoute(
                                breed = it.name.lowercase()
                            )
                        )
                    }
                )
            }
            composable(
                route = ScreenDestination.DogBreedDetailScreen.route,
                arguments = ScreenDestination.DogBreedDetailScreen.navArguments
            ) {
                val viewModel = hiltViewModel<DogBreedRandomImageViewModel>()
                val uiState by viewModel.uiState.collectAsState()
                DogBreedItemDetailScreen(
                    navController = navController,
                    uiState = uiState
                )
            }
        }
    }
}