package com.example.sample_app

import android.app.Application
import com.example.sample_app.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.Theme_SAMPLE_APP)
    }
}