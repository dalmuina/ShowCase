plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.dalmuina.showcase"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dalmuina.showcase"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug{
            buildConfigField("String","BASE_URL","\"https://api.rawg.io/api/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String","BASE_URL","\"https://api.rawg.io/api/\"")
        }
    }

    configurations.implementation{
        exclude(group = "com.intellij", module = "annotations")
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    lint {
        checkReleaseBuilds = false
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,LICENSE.md,LICENSE-notice.md}"
        }
    }
}

dependencies {

    //Modules
    api(project(":core:utils"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    api(project(":core:model"))
    implementation(project(":core:model-ui"))
    implementation(project(":core:network"))
    implementation(project(":core:designsystem"))

    //Core
    implementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.bundles.compose.debug)
    coreLibraryDesugaring(libs.desugar.jdk.libs)
    implementation (libs.material)

    //Compose
    implementation(libs.bundles.compose){
        exclude(module="androidx.compose.material3.adaptive:adaptive-navigation")
        exclude(group="org.jetbrains.kotlinx",module="kotlinx-serialization-json")
    }

    //Corrutinas
    runtimeOnly(libs.kotlinx.coroutines.android)

    //Ktor
    implementation(libs.bundles.ktor) {
        exclude(group = "io.ktor", module="ktor-client-content-negotiation")
        exclude(group = "io.ktor", module="ktor-client-core")
        exclude(group = "io.ktor", module="ktor-client-logging")
    }

    //Koin
    implementation(libs.bundles.koin)

    //Paging
    implementation(libs.bundles.paging) {
        exclude(module="androidx.paging:paging-runtime")
    }

    //SplashScreen
    implementation(libs.androidx.core.splashscreen)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugRuntimeOnly(libs.androidx.ui.test.manifest)
    testImplementation (libs.turbine)
    testImplementation(libs.truth)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation (libs.koin.test)
    testImplementation(libs.androidx.core.testing)

}
