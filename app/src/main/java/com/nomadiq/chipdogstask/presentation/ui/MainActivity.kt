package com.nomadiq.chipdogstask.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.nomadiq.chipdogstask.presentation.ui.navigation.DogBreedsApp
import com.nomadiq.chipdogstask.presentation.ui.theme.ChipDogsTaskTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 *  @author Michael Akakpo
 *
 *  MainActivity hosting the NvHost and all navigation
 *
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChipDogsTaskTheme {
                DogBreedsApp()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChipDogsTaskTheme {
        DogBreedsApp()
    }
}

