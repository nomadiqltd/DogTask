plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt") version "1.9.22"
}

android {
    namespace = "com.nomadiq.chipdogstask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nomadiq.chipdogstask"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        // Allow references to generated code
        correctErrorTypes = true
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {
    // Ktx Core Kotlin
    implementation("androidx.core:core-ktx:1.12.0")

    // Lifecycle components
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Activity for ComponentSelection UI
    implementation("androidx.activity:activity-compose:1.8.2")

    // Compose Bom / UI
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui-android:1.6.3")
    implementation("androidx.compose.ui:ui-graphics-android:1.6.3")

    // Image Loading
    implementation("io.coil-kt:coil-compose:2.6.0")

    implementation("androidx.compose.material3:material3:1.2.1")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    // Optional - Integration with LiveData
    implementation("androidx.compose.runtime:runtime-livedata:1.6.3")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Networking Retrofit 2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")

    // Moshi converters
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")
    implementation("com.squareup.moshi:moshi:1.14.0")

    // Logging Network call
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Data Mapping Serialisation
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0")

    // Coroutines / Asynchronous operations / Flows
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Kotlin - Nav Args
    implementation("androidx.savedstate:savedstate-ktx:1.2.1")

    // Dependency Injection
    implementation("com.google.dagger:hilt-android:2.51")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    kapt("com.google.dagger:hilt-android-compiler:2.50")

    // Hilt - For instrumentation tests
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51")
    kapt("com.google.dagger:hilt-compiler:2.51")

    // Hilt - For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.51")
    kapt("com.google.dagger:hilt-compiler:2.51")

    // Compose preview
    implementation("androidx.compose.ui:ui-tooling-preview-android:1.6.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // For Coroutines testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // JUnit4
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("junit:junit:4.13.2")

    // JUnit5
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // For Kotest
    testImplementation("io.kotest:kotest-assertions-core:5.4.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Mockito
    testImplementation("org.mockito:mockito-core:5.2.0")

    // Google Truth - Assertions
    testImplementation("com.google.truth:truth:1.4.2")
    testImplementation("com.google.truth:truth:1.4.2")

    // Hamcrest Assertions
    testImplementation("org.hamcrest:hamcrest:2.2")
    androidTestImplementation("org.hamcrest:hamcrest:2.2")

    // For Mockk
    testImplementation("io.mockk:mockk:1.13.4")

    // Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Junit Testing
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Testing Compose
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))

    // TODO() - Could utilise new Android versioning catalog - libs.versions.toml
}
