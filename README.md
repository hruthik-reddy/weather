# weather-app-clean-architecture
 <div style={display:flex;}>

<img src="https://img.shields.io/github/languages/code-size/bijaykumarpun/weather-app-clean-architecture?style=flat-square"/>

<img src="https://img.shields.io/github/last-commit/bijaykumarpun/weather-app-clean-architecture?style-flat-square"/>

</div><br>

A simple weather app that shows daily weather conditions, and weather forecasts; built with `Kotlin` using `MVVM` and `Clean Architecture` design pattern.

### Screenshots
![screenshot](https://github.com/bijaykumarpun/weather-app-clean-architecture/assets/13991373/16f0084b-deb3-457a-84d9-6092aac95bee)


### Recording
[recording-implementation-1.webm](https://github.com/bijaykumarpun/weather-app-clean-architecture/assets/13991373/fb3aa458-ca48-422f-acfc-1f440fa6eadc)



### Technologies

    - MVVM with Clean Architecture for design pattern
    - Hilt for dependency injection
    - Jetpack Compose for UI component design
    - Retrofit & OkHttp for network requests
### API
This project uses API data from [weatherapi.com](https://www.weatherapi.com/)

### Folder Structure
```bash
.
├── LICENSE
├── README.md
├── app
│   ├── build. gradle
│   ├── proguard-rules.pro
│   └── src
│       ├── androidTest
│       ├── main
│       │   ├── AndroidManifest.xml
│       │   ├── java
│       │   │   └── global
│       │   │       └── x
│       │   │           └── weather
│       │   │               ├── XWeatherApplication.kt
│       │   │               ├── data
│       │   │               │   ├── repositories
│       │   │               │   └── source
│       │   │               │       ├── device
│       │   │               │       └── weather
│       │   │               ├── domain
│       │   │               │   ├── models
│       │   │               │   └── use_cases
│       │   │               │       ├── device
│       │   │               │       └── weather
│       │   │               ├── infrastructure
│       │   │               │   └── di
│       │   │               └── presentation
│       │   │                   ├── framework
│       │   │                   │   └── theme
│       │   │                   └── screen
│       │   │                       ├── home
│       │   │                       │   └── model
│       │   │                       ├── settings
│       │   │                       └── weather_detail
│       │   └── res
│       │       ├── drawable
│       │       ├── values
│       │       └── xml
│       └── test
├── build.gradle
├── gradle
│   └── wrapper
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
└── settings.gradle
```

### Data Flow
<img width="929" alt="DFD" src="https://github.com/bijaykumarpun/weather-app-clean-architecture/assets/13991373/ad496208-1e07-42ac-8c3b-81a33d3394fc">


### Setup Instruction
You can setup/run the project in two ways - either by downloading the project and compiling locally using Android Studio, or by downloading and installing the debug APK artifact straight from the build pipeline.

#### Run the project locally

> Make sure you have all the local dependencies setup i.e Android Studio & the Android SDK, [check here](https://developer.android.com/studio/install)

- First off, `git clone` this project
    - `git clone https://github.com/bijaykumarpun/weather-app-clean-architecture`
- Open the project in `Android Studio`pointing to the root folder's `build.gradle` file
- After successfully building the project, run `./gradlew installDebug` in the root project directory to install in any of the available device/emulator

#### Download APK from the CI pipeline
You can also get the latest debug APK file directly from the CI pipeline
- Navigate to the `Actions` tab of this GitHub project
- Look for the most recent CI run
- Under `Artifact` section look for `debug-build` file
- Click `debug-build` to download the file
- Extract & install

### Design Inspiration
- [Offdesignarea's Weather App design in Dribbble](https://dribbble.com/shots/17003404-Weather-App)

### License
<img src="https://img.shields.io/badge/license-MIT-brightgreen?style=flat-square"/>
