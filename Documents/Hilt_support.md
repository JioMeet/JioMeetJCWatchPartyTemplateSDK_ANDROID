# Setup Hilt in your project

To set up Hilt in your Android project, follow these steps:

1. First, add the hilt-android-gradle-plugin plugin to your project’s root build.gradle file:

   ```gradle
   plugins {
   id("com.google.dagger.hilt.android") version "2.44" apply false
   }
   ```

2. Add the Hilt dependencies to the app-level build.gradle file

   ````gradle
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
   } ```
   ````

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


3. In /app/java/com.example.<projectname>/MainActivity, add @AndroidEntryPoint to enable Hilt injection. Here's an example:

```kotlin
   import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // ...
}
```
