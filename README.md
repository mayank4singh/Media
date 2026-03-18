# 📤 MediaUpload — Android Media Upload App

[![Kotlin](https://img.shields.io/badge/Kotlin-100%25-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white)](https://developer.android.com/)
[![Gradle](https://img.shields.io/badge/Build-Gradle%20Kotlin%20DSL-02303A?logo=gradle)](https://gradle.org/)

A modern **Android media upload application** built with **Kotlin** and **Jetpack Compose**. The app allows users to select and upload media files (images, videos) from their device storage with a clean, modern UI.

---

## 📱 About The App

**MediaUpload** is a native Android application that demonstrates media handling on Android — picking files from device storage, previewing them, and uploading them. Built with the latest Android development tools including Jetpack Compose for a fully declarative UI.

---

## ✨ Features

- 📷 **Media Selection** — Pick images and videos from device storage
- 👀 **Media Preview** — Preview selected media before uploading
- 📤 **Media Upload** — Upload selected files to a remote server or cloud
- 🎨 **Modern UI** — Built entirely with Jetpack Compose
- 🔐 **Runtime Permissions** — Handles Android storage permissions gracefully
- ⚡ **Fast & Lightweight** — Minimal dependencies, optimized performance

---

## 🛠️ Tech Stack

| Component | Technology |
|---|---|
| Language | Kotlin|
| UI Framework | Jetpack Compose |
| Build System | Gradle with Kotlin DSL |
| Architecture | Single Activity |
| IDE | Android Studio |
| Min SDK | Android 5.0 (API 21) |
| Target SDK | Android 14 (API 34) |

---

## 📂 Project Structure

```
Media/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/mediaupload/
│   │   │   │   └── MainActivity.kt     # Main entry point
│   │   │   ├── res/                    # Resources
│   │   │   └── AndroidManifest.xml     # App manifest
│   │   └── test/                       # Unit tests
├── gradle/
│   └── wrapper/                        # Gradle wrapper
├── build.gradle.kts                    # Project build config
├── settings.gradle.kts                 # Project settings
└── gradle.properties                   # Gradle properties
```

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- JDK 17 or higher
- Android SDK (API 21+)
- Android device or emulator

### Clone the Repository

```bash
git clone https://github.com/mayank4singh/Media.git
cd Media
```

### Open in Android Studio

1. Open **Android Studio**
2. Click **File → Open**
3. Navigate to and select the `Media` folder
4. Wait for **Gradle sync** to complete automatically
5. Select a device/emulator and click **Run ▶️**

### Build via Terminal

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run unit tests
./gradlew test

# Clean build
./gradlew clean build
```

---

## 📋 Permissions

The app requires the following Android permissions:

```xml
<!-- For reading media files (Android 13+) -->
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
<uses-permission android:name="android.permission.READ_MEDIA_VIDEO" />

<!-- For older Android versions -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

<!-- For network upload -->
<uses-permission android:name="android.permission.INTERNET" />
```

---

## 📸 How It Works

```
User opens app
      │
      ▼
Grant storage permission
      │
      ▼
Select media from gallery / camera
      │
      ▼
Preview selected media
      │
      ▼
Confirm and upload
      │
      ▼
Upload progress shown
      │
      ▼
Success / Error feedback
```


---

<p align="center">
  <b>Built with ❤️ and lots of ☕ by <a href="https://github.com/mayank4singh">Mayank Singh</a></b>
</p>
