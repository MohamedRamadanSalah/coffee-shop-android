# Coffee Shop (Android)

![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white) ![Java](https://img.shields.io/badge/Java-ED8B00?logo=openjdk&logoColor=white) ![minSdk](https://img.shields.io/badge/minSdk-24-blue) [![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A native Android coffee-ordering app in Java: explore the menu by category, view drink details, manage a cart and favourites, and place orders. The menu loads from **Firebase Realtime Database**, and the cart and favourites are saved on the device.

## Features

- Menu explorer with categories and item lists
- Item details with size and quantity selection
- Cart with live quantity and total updates (`ManagementCart`)
- Favourites (`ManagementFavourite`) and order history
- User profile
- Local persistence with SharedPreferences (TinyDB)

## Tech stack

Java · Android SDK (minSdk 24, targetSdk 35) · Material Components · ConstraintLayout · RecyclerView · Firebase Realtime Database · SharedPreferences · Gradle (Kotlin DSL)

## Firebase setup

The Firebase config file is not committed. To run the app:

1. Create a Firebase project and add an Android app with the package name shown in `app/build.gradle.kts`.
2. Enable **Realtime Database** and import your data.
3. Download `google-services.json` into `app/`.

## License

[MIT](LICENSE) © 2025 Mohamed Ramadan
