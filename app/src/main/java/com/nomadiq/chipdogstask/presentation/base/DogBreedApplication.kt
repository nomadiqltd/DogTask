package com.nomadiq.chipdogstask.presentation.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author - Michael Akakpo
 *
 * Base Application class used to provide App context to areas of the app
 * that require it, through [@Dagger/Hilt]
 *
 */
@HiltAndroidApp
class DogBreedApplication : Application()