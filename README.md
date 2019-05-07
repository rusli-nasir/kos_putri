# Aplikasi Kost Putri Denpasar


### Android
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

### Web Server

Lokasi web server berada di [web_server/kostputridps](https://github.com/rusli-nasir/kos_putri/tree/master/web_server/kostputridps), silahkan dipindakhan ke vebserver yang telah di install misalkan pada htdocs di aplikasi XAMPP

dan untuk koneksi silahkan ubah pada web_server/kostputridps/config/koneksi.php
```sh
$server = "localhost";
$username = "root";
$password = "";
$database = "kosputridps";
$port = 3306; //Default 3306

$gmap_key = 'GMAP_KEY';

// Koneksi dan memilih database di server
mysql_connect($server . ':' . $port,$username,$password) or die("Koneksi gagal");
mysql_select_db($database) or die("Database tidak bisa dibuka");
```
