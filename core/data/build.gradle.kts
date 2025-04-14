plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.dalmuina.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":core:network"))
    api(project(":core:domain"))
    api(project(":core:utils"))
    api(project(":core:model"))
    api(project(":core:api-client"))

    //Ktor
    implementation(libs.bundles.ktor) {
        exclude(group = "io.ktor", module="ktor-client-content-negotiation")
        exclude(group = "io.ktor", module="ktor-client-logging")
    }

    implementation(libs.androidx.appcompat)
    implementation(libs.bundles.paging)
    //Koin
    implementation(libs.bundles.koin)
}