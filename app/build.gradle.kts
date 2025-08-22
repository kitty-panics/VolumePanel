plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "kitty.panics.volumepanel"
    compileSdk = 36

    defaultConfig {
        applicationId = "kitty.panics.volumepanel"
        minSdk = 26
        targetSdk = 36
        versionCode = 101
        versionName = "1.0.1"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(libs.androidx.core.ktx)
    //implementation(libs.androidx.activity)
    //implementation(libs.androidx.appcompat)
}