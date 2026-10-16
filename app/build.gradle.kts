plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.autsing.imeswitcher"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.autsing.imeswitcher"
        minSdk = 29
        targetSdk = 36
        versionCode = 12000
        versionName = "1.2.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.libsu)
}
