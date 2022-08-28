<h1 align="center">NyTimes for Clean Architecture</h1>

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


