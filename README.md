# Aplikasi Kost Putri Denpasar

Pastikan keperluan SDK pada android studio telah di update ke versi buildToolsVersion '28.0.3'

```sh
android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.kos_putri"
        minSdkVersion 19
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    buildToolsVersion '28.0.3'
}
```
