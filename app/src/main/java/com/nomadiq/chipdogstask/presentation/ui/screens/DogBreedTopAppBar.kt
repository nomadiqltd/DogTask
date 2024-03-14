package com.nomadiq.chipdogstask.presentation.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.nomadiq.chipdogstask.R

/**
 * @author Michael Akakpo
 *
 * Composable that displays the topBar and displays back button if back navigation is possible.
 *
 * Ideally to be used to be used within a specific nest of the navigation graph or "back" action could yield undesirable results.
 */
// TODO - Could have used CenterAlignedTopToolbar for root and TopAppBar for detail screen, but just reused for demo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogBreedTopAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    title: String
) {
    TopAppBar(
        title = {
            Text(title)
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.toolbar_title_text)
                    )
                }
            }
        }
    )
}