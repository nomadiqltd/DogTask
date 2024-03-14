# Dog Breeds App

This is a Jetpack Compose Android app which displays images of selected Dog Breeds and view the detail images.

# Prerequisites: 

**Minimum Android SDK Version:** 24

**Target Android SDK Version:** 34

**App Version 1.0.0**

**Build System**
Gradle
Kotlin

# Architecture
The chosen architecture is MMVM (Model View ViewModel) with a Clean-ish approach. 

The main 3 layers are: 

**Presentation Layer**
- This layer displays the application data on the screen. This includes the Jetpack Compose functions, ViewModels and UI State.

**Domain Layer**
- The domain layer is the mediator between the data layer and the presentation layer.
- This app is pretty small but for future scalability it will become more complex and all that logic that would have lived in the ViewModel would now reside in UseCases instead (optional layer).

**Data Layer**
- The data layer contains repositories that abstract away the complexity of the data's origins. In this case it's all remote data but it has the capability to extend to a Databse or other form of local storage.

**Project Dependencies**

Below is a brief summary of the main dependencies below:

# **Jetpack libraries**
Compose
- Declarative UI with composable functions as opposed to old school XML layouts
  
Arch Core
- Helper for other arch dependencies, including JUnit test rules.

Hilt
- Dependency injection to allow the different components to best utlisie their dependencies.

Navigation
- Default navigation library (kept it default) - but could have leveraged any other third party library.

Kotlinx Coroutines
- Asynchronous programming to obtain data from the network.

Retrofit
- HTTP client that supports coroutines.
  
Moshi
- JSON Parser, used to parse requests on the data layer for Entities (coupled with okhhtp interceptors)

JUnit
- This was used for unit testing the use cases, and the ViewModels, Repositories.
  
Mockk / Mockito
 - Used to provide test fakes / doubles to aid in testing other components by reducing side effects of their intrinsic dependencies.

Truth
 - Readable Assertions Library to aid test fidelity

Espresso
 - Used for writing Android UI tests (that require the Android system components i.e. Activities, some Composables)

Coil
 - Image loading for Android backed by Kotlin Coroutines.

# **Testing**

Coverage can always be improved. Included a sample of various test types to cover all three layers. Mainly unit, integrated, instrumented tests
- @Composables, ViewModels, Usecases, Repositories have automated tests


💻 Demo
An app walkthrough can arranged to talk through the various layers and rationale behind some design decisions. 

# **Run the app:**
On Github, check out the 'main' branch
Press play 
Command: gradle assembledDebug

# **Known Improvements: **

- Theming and UI is incomplete (could use some colours from the Chip design palette)
- The toolbar navigation works well with smaller less complex app, but in a larger app could require a different approach with third parties depending on the use case etc
- Not in the spec but state management to enhance the UX - i.e. loading/error screens
- Offline support - use of a snackbar to inform userr there is poor or no connectivity
- Persist the Dogs so the user always has access even when offline

# **Out of Scope**
- Handling of subbreeds
- Offline mode
- Minor testing in obfuscated / Proguard mode only
