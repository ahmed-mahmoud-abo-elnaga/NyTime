<h1 align="center">NyTimes for Clean Architecture</h1>

<p align="center">
  <a href="https://developer.android.com/jetpack/androidx/releases/compose"><img alt="JetpackCompose" src="https://img.shields.io/badge/Jetpack%20Compose-1.1.0--beta03-blueviolet"/></a>
  <a href="https://developer.android.com/reference"><img alt="Platform" src="https://img.shields.io/badge/platform-android-brightgreen.svg"/></a>
  <a href="https://androidstudio.googleblog.com/2022/05/android-studio-dolphin-beta-1-now.html"><img alt="Android Studio Dolphin" src="https://img.shields.io/badge/AS%20Dolphin-2021.3.1%20Patch%204-9cf.svg"/></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>

<p align="center">
The purpose of this project is to consolidate some of the learned insights throughout the years about the <b>Clean Architecture</b> principles and reflect those lessons on Android, taking advantage of the Kotlin programming language features too.
<br/><br>This project summarises some of the general use cases and demands on a typical production project using: <b>Jetpack Compose</b>, <b>Functional Programming</b>,<b>MVI</b>, <b>Kotlin Coroutines</b> and <b>Kotlin Flows</b></p> 
Build a simple app to hit the NY Times Most Popular Articles API and show a list of articles, that
shows details when items on the list are tapped.

## Libraries Used :books:

* [Compose][0] Toolkit for building native UI (in a declarative way - 100% Kotlin).
* [Coroutines][1] Library support for Kotlin coroutines.
* [Flows][2] Stream processing API, built on top of Coroutines.
* [Dagger Hilt][3] Dependency injection library for Android.
* [Retrofit][4] Type-safe REST client for Android to consume RESTful web services.
* [Timber][5] Logger with a small API which provides utility on top of Android's normal Log class.
* [MockK][6] mocking framework for testing.
* [Coil Compose][7] Image downloading and caching library supported by Jetpack Compose.
* [Lottie Compose][8] Library that provides that parses Adobe After Effects animations exported as
  json with Bodymovin and renders them natively on mobile.
* [Shot][9] Shot is a Gradle plugin and a core android library thought to run screenshot tests for
  Android.

[0]:  https://developer.android.com/jetpack

[1]:  https://github.com/Kotlin/kotlinx.coroutines

[2]:  https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/

[3]:  https://dagger.dev/hilt/

[4]:  https://github.com/square/retrofit

[5]:  https://github.com/JakeWharton/timber

[6]:  https://mockk.io

[7]: https://github.com/coil-kt/coil

[8]: https://airbnb.io/lottie/#/android-compose

[9]: https://github.com/pedrovgs/Shot

<img src="/art/demo.gif" align="right" width="32%"/>
## Modules :bookmark_tabs:

* **app** - The application module with access to **all the application**
* **data** - Android module that **can only access domain module**
* **data-api** - Android module that **can only access data module**
* **domain** - Kotlin module that **cannot access any other module**

### Unit Testing

There are some highlights:

* Every layer in the architecture has been tested.
* MockK has been used for mocking | stubbing.
* `Given | When | Then` code presentation order, in order to give a more structured style.

### Clean Architecture 
<p align="center">
<img src="/art/clean_architecture_dark.jpg"/>
</p>


## License :oncoming_police_car:
    Copyright 2023 Ahmed Abo Elnaga

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


