plugins {
    id("com.android.application")
}

android {
    namespace = "com.rishimahendra.rozana"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.rishimahendra.rozana"
        minSdk = 29
        targetSdk = 36
        versionCode = 3
        versionName = "1.1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    signingConfigs {
        create("playUpload") {
            storeFile = file("../upload-key.p12")
            storeType = "pkcs12"
            storePassword = System.getenv("ROZANA_UPLOAD_KEY_PASSWORD") ?: ""
            keyAlias = "rozana-upload"
            keyPassword = System.getenv("ROZANA_UPLOAD_KEY_PASSWORD") ?: ""
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("playUpload")
        }
    }
}
