plugins {
    id("com.android.application")
}

android {
    namespace = "devandroid.paulo.appvvspdvmobile"
    compileSdk = 33

    defaultConfig {
        applicationId = "devandroid.paulo.appvvspdvmobile"
        minSdk = 26
        targetSdk = 33
        versionCode = 2
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //biblioteca de mascara telefone - https://github.com/santalu/maskara
    implementation("com.github.santalu:maskara:1.0.0")

    //biblioteca para mascara no editText - https://github.com/VicMikhailau/MaskedEditText
    implementation("io.github.vicmikhailau:MaskedEditText:5.0.1")

    //biblioteca para imagem gif //https://github.com/bumptech/glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //biblioteca para converter Json em Class
    implementation("com.google.code.gson:gson:2.8.8")

    //biblioteca para solicitacao HTTP API - https://github.com/square/retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


}