# JioMeetJCWatchPartyTemplateSDK_ANDROID

**Welcome to Jiomeet Template UI Kit**, an SDK that simplifies the integration of JioMeet's robust audio and video capabilities, along with exciting features like reactions and chat, into your Android application with minimal coding effort. With just a few simple steps, you can enable high-quality real-time communication, allowing users to effortlessly connect, interact, and enjoy watching content together.

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Prerequisites](#prerequisites)
   - [Hilt](#hilt)
   - [Jetpack Compose](#jetpack-compose)
4. [Setup](#setup)
   - [Register on JioMeet Platform](#register-on-jiomeet-platform)
   - [Get Your Application Keys](#get-your-application-keys)
   - [Get Your JioMeet Meeting ID and PIN](#get-your-jiomeet-meeting-id-and-pin)
5. [Configure JioMeet Template UI Inside Your App](#configure-jiomeet-template-ui-inside-your-app)
   - [Add permissions for network and device access](#add-permissions-for-network-and-device-access)
   - [Initiliazing Hilt in Application Class](#initialize-hilt-in-application-class)
   - [Start Your App](#start-your-app)
6. [Sample App](#sample-app)
7. [Troubleshooting](#troubleshooting)

## Introduction

In this documentation, we'll guide you through the process of installation, enabling you to enhance your Android app with Jiomeet's real-time communication capabilities swiftly and efficiently.Let's get started on your journey to creating seamless communication experiences with Jiomeet WatchParty Template UI!

![image info](./images/watch_party.png)

---

## Features

In Jiomeet Template UI, you'll find a range of powerful features designed to enhance your Android application's communication and collaboration capabilities. These features include:

1. **Voice and Video Streaming**:Enjoy high-quality, real-time audio and video streaming while watching content with your friends.

2. **Reactions** Allow participants to express their reactions and emotions while watching, enhancing the sense of togetherness

3. **Chat Integration**: Enable real-time chat for participants to discuss the content, share thoughts, and socialize during the watch party.

![image info](./images/watch_Party.png)

## Prerequisites

Before you begin, ensure you have met the following requirements:

#### Hilt:

To set up Hilt in your Android project, follow these steps:

1. First, add the hilt-android-gradle-plugin plugin to your project’s root build.gradle file:

   ```gradle
   plugins {
   id("com.google.dagger.hilt.android") version "2.44" apply false
   }
   ```

2. Add the Hilt dependencies to the app-level build.gradle file

   ```gradle
   plugins {
     kotlin("kapt")
     id("com.google.dagger.hilt.android")
   }

   android {
       ...
       compileOptions {
           sourceCompatibility = JavaVersion.VERSION_11
           targetCompatibility = JavaVersion.VERSION_11
       }
   }

   dependencies {
           implementation "androidx.hilt:hilt-navigation-compose:1.0.0"
           implementation "com.google.dagger:hilt-android:2.44"
           kapt "com.google.dagger:hilt-android-compiler:2.44"
   }

   ```

#### Jetpack Compose:

JioMeet Template UI relies on Jetpack Compose for its user interface components.
Ensure that your Android project is configured to use Jetpack Compose. You can add the necessary configurations to your project's build.gradle file:

```gradle
  // Enable Jetpack Compose
  buildFeatures {
      compose true
  }

  // Set the Kotlin compiler extension version for Compose
  composeOptions {
      kotlinCompilerExtensionVersion = "1.3.2"
  }
```

---

## Setup

##### Register on JioMeet Platform:

You need to first register on Jiomeet platform.[Click here to sign up](https://platform.jiomeet.com/login/signUp)

##### Get your application keys:

Create a new app. Please follow the steps provided in the [Documentation guide](https://dev.jiomeet.com/docs/quick-start/introduction) to create apps before you proceed.

###### Get you Jiomeet meeting id and pin

Use the [create meeting api](https://dev.jiomeet.com/docs/JioMeet%20Platform%20Server%20APIs/create-a-dynamic-meeting) to get your room id and password

## Configure JioMeet Template UI inside your app

i. **Step 1** : Generate a Personal Access Token for GitHub

- Settings -> Developer Settings -> Personal Access Tokens -> Generate new token
- Make sure you select the following scopes (“ read:packages”) and Generate a token
- After Generating make sure to copy your new personal access token. You cannot see it again! The only option is to generate a new key.

ii. Update build.gradle inside the application module

```kotlin
    repositories {
    maven {
        credentials {
            <!--github user name-->
                username = ""
            <!--github user token-->
                password = ""
        }
        url = uri("https://maven.pkg.github.com/JioMeet/JioMeetJCWatchPartyTemplateSDK_ANDROID")
    }
    google()
    mavenCentral()
}
```

iii. In Gradle Scripts/build.gradle (Module: <projectname>) add the Template UI dependency. The dependencies section should look like the following:

```gradle
dependencies {
    ...
    implementation "com.jiomeet.platform:jiomeetwatchpartytemplatesdk:<version>"
    ...
}
```

Find the [Latest version](https://github.com/JioMeet/JioMeetJCWatchPartyTemplateSDK_ANDROID/releases) of the UI Kit and replace <version> with the one you want to use. For example: 2.3.0.

### Add permissions for network and device access.

In /app/Manifests/AndroidManifest.xml, add the following permissions after </application>:

```gradle

<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<!-- The SDK requires Bluetooth permissions in case users are using Bluetooth devices. -->
<uses-permission android:name="android.permission.BLUETOOTH" />
<!-- For Android 12 and above devices, the following permission is also required. -->
<uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />
```

### Requesting run time permissions

it's crucial to request some permissions like **\_CAMERA ,RECORD_AUDIO** at runtime since these are critical device access permissins to ensure a seamless and secure user experience. Follow these steps

1. Check Permissions

```kotlin
if (checkPermissions()) {
    // Proceed with using the features.
} else {
    // Request critical permissions at runtime.
}
```

2. Request Runtime Permissions:

```kotlin
private void requestCriticalPermissions() {
    ActivityCompat.requestPermissions(this,
        new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        },
        PERMISSION_REQUEST_CODE);

}
```

3. Handle Permission Results

```kotlin
@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == PERMISSION_REQUEST_CODE) {
        if (areAllPermissionsGranted(grantResults)) {
            // Proceed with using the features that require critical permissions.
        } else {
            // Handle denied permissions, especially for camera and phone state, which are essential.
        }
    }
}
```

### Initiliazing Hilt in Application Class

1. Create a Custom Application Class: If your users don't already have a custom Application class in their Android project, they should create one. This class will be used to initialize Hilt.

```kotlin
import android.app.Application;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
class MyApplication : Application {
    // ...
}
```

2. Modify AndroidManifest.xml: In the AndroidManifest.xml file of their app, users should specify the custom Application class they created as the application name. This tells Android to use their custom Application class when the app starts.

```xml
<application
    android:name=".MyApplication" <!-- Specify the name of your custom Application class -->
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/AppTheme">
    <!-- ... -->
    </application>
```

### Start your App

In /app/java/com.example.<projectname>/MainActivity, add @AndroidEntryPoint to enable Hilt injection. Here's an example:

```kotlin
   import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // ...
}
```

update onCreate to run LaunchCore() when the app starts. The updated code should like the provided code sample:

```kotlin
    private val jioMeetConnectionListener = object : JCConnectionListener {
        override fun onShareInviteClicked(meetingId: String, meetingPin: String, name: String) {
        }
        override fun closeWatchParty() {
        }
        override fun onAnalyticsEvent(analyticsEvent: AnalyticsEvent) {
        }
        override fun showNativeLogin(isShow: Boolean) {
        }
        override fun onMediaVolumeChange(volume: Int) {
        }
        override fun partyStarted(meetingId: String) {
        }
    }
    val watchPartyListener = remember {
                mutableStateOf(WatchPartyListener(jioMeetConnectionListener))
            }
    WatchPartyNav(
                    modifier = Modifier,
            watchPartyData = WatchPartyData(
                //pass clientToken
                clientToken = "",
                meetingID = "",
                isUserJoiner = false,
                meetingPin = "",
                partyGuestName = "",
                isLoggedInUser = false,
                userName = "Test",
                watchPartyListener = watchPartyListener,
            ),
                )

```

The JCConnectionListener interface allows you to receive important events and callbacks related to a Jio-Meet session. You can implement this interface to handle various events that occur during a meeting, such as participants joining or leaving, errors, analytics events, and more. Below are the available callbacks, use can use these callbacks to implement custom behaviour

- **_onShareInviteClicked_**(meetingId: String, meetingPin: String, name: String)
  ```Kotlin
  override fun onShareInviteClicked(meetingId: String, meetingPin: String, name: String) {
      // Implement custom behavior for sharing meeting details
  }
  ```
- **_closeWatchParty_**()
  ```Kotlin
  override fun closeWatchParty() {
      finish()
  }
  ```
- **_onAnalyticsEvent_**(analyticsEvent: AnalyticsEvent)
  ```Kotlin
  override fun onAnalyticsEvent(analyticsEvent: AnalyticsEvent) {
      // Log or track analytics event information
  }
  ```
- **_Other Callbacks_**
  Depending on your use case, there are additional callbacks available in the JCConnectionListener interface for handling various aspects of the meeting, such as **media_volume** and more.
  Please implement the necessary callbacks in your JCConnectionListener implementation to customize the behavior of your JioMeet integration as per your application's requirements.

## Sample app

Visit our [Jiomeet Template UI Sample app](https://github.com/JioMeet/JioMeetJCWatchPartyTemplateSDK_ANDROID) repo to run the Sample app.

---

## Troubleshooting

- Facing any issues while integrating or installing the JioMeet WatchParty Template UI Kit please connect with us via real time support present in jiomeet.support@jio.com or https://jiomeetpro.jio.com/contact-us

---
