package com.nomadiq.chipdogstask.presentation.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nomadiq.chipdogstask.R
import com.nomadiq.chipdogstask.presentation.ui.screens.DogBreedListScreen
import com.nomadiq.chipdogstask.presentation.viewmodel.DogBreedListViewModel


/**
 * enum values that represent the screens in the app
 */
enum class CurrentScreen(@StringRes val title: Int) {
    DogBreedScreen(title = R.string.toolbar_title_dogbreed_list),
    DogBreedDetailScreen(title = R.string.toolbar_title_dogbreed_detail)
}


/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogBreedTopAppBar(
    currentScreen: CurrentScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
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
    val currentScreen = CurrentScreen.valueOf(
        backStackEntry?.destination?.route ?: CurrentScreen.DogBreedScreen.name
    )

    Scaffold(
        topBar = {
            DogBreedTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
            startDestination = CurrentScreen.DogBreedScreen.name
        ) {
            composable(route = CurrentScreen.DogBreedScreen.name) {
                DogBreedListScreen(
                    navController = navController,
                    uiState = uiState
                )
            }
            composable(route = CurrentScreen.DogBreedDetailScreen.name) {
                //  DogBreedList(navController)
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopTitleOnlyNavigationAppBar(title: String) {
    TopAppBar(
        title = { Text(text = title) },
    )
    Spacer(modifier = Modifier.height(64.dp))
}