## Configure JioMeet Core SDK inside your app


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
        url = uri("implementation "com.jiomeet.platform:jiomeetwatchpartytemplatesdk:<version>"")
    }
    google()
    mavenCentral()
}
```

iii. In Gradle Scripts/build.gradle (Module: <projectname>) add the CORE dependency. The dependencies
section should look like the following:

```gradle
dependencies {
    ...
    implementation "com.jiomeet.platform:jiomeetcoresdk:<version>"
    ...
}
```

Find the [Latest version](https://github.com/JioMeet/JioMeetJCWatchPartyTemplateSDK_ANDROID/releases) of the JioMeet WatchpartySDK
SDK and replace <version> with the one you want to use. For example: 3.0.0.

### Add permissions for network and device access.

In /app/Manifests/AndroidManifest.xml, add the following permissions after </application>:

```gradle
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>
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

it's crucial to request some permissions like **_CAMERA ,RECORD_AUDIO, READ_PHONE_STATE_** at
runtime since these are critical device access permissins to ensure a seamless and secure user
experience. Follow these steps

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
  ActivityCompat.requestPermissions(
    this,
    new String []{
      Manifest.permission.READ_PHONE_STATE,
      Manifest.permission.CAMERA,
      Manifest.permission.RECORD_AUDIO
    },
    PERMISSION_REQUEST_CODE
  );

}
```

3. Handle Permission Results

```kotlin
@Override
public void onRequestPermissionsResult(
  int requestCode,
  @NonNull String[] permissions,
  @NonNull int[] grantResults
) {
  if (requestCode == PERMISSION_REQUEST_CODE) {
    if (areAllPermissionsGranted(grantResults)) {
      // Proceed with using the features that require critical permissions.
    } else {
      // Handle denied permissions, especially for camera and phone state, which are essential.
    }
  }
}
```

---

### Initialisation

- The `MainApplication` class, by extending `CoreApplication` or  calling `CoreApplication().recreateModules(this)`, ensures that your application is correctly initialized to incorporate JioMeet Core SDK features seamlessly.

- **Please consult the [Hilt Setup Guide](./Hilt_support.md) to streamline the integration with Hilt.**

- Update onCreate to run LaunchCore() when the app starts. The updated code should like the provided code sample:

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





