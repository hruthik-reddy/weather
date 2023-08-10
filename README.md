# weather-app-clean-architecture

### Technologies
- Architecture: MVVM with Clean Architecture
- Dependency Injection: Hilt
- UI Design: Jetpack Compose
- Network Requests: Retrofit & OkHttp

### Architecture
- MVVM with Clean Code Architecture

### Data Flow
- presentation <-> domain <-> data

### To get the latest debug APK file

#### Build locally
You can build the project and generate APK file on your local machine. Make sure you have the latest Android SDK installed.
- First off, `git clone` this project locally
- Open the project using `Android Studio` pointing to the project's `build.gradle` file
- After successfully building the project, run `./gradlew assembleDebug` in the root project directory
- The debug APK is built inside `app/build/outputs/apk/debug/` 

#### Get from the CI run
You can also get the latest debug APK file directly from the CI run
- Navigate to `Actions`
- Look for the most recent CI run
- Under `Artifact` section look for `debug-build` file
