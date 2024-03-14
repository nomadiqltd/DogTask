package com.nomadiq.chipdogstask.presentation.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

/**
 *
 * @author - Michael Akakpo
 *
 * Predefined set of values that represent all the possible screens destinations in the app nav graph
 */
sealed class ScreenDestination(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    data object DogBreedListScreen : ScreenDestination(route = "listScreen")

    data object DogBreedDetailScreen : ScreenDestination(
        route = "itemDetail/{breed}",
        navArguments = listOf(navArgument(name = "breed") {
            type = NavType.StringType
        })
    ) {
        fun createRoute(breed: String) = "itemDetail/${breed}"
    }
}