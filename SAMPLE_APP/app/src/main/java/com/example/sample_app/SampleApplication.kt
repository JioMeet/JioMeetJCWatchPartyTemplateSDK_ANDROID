package com.example.sample_app

import android.app.Application
import com.jiomeet.core.CoreApplication
import com.jiomeet.core.constant.Constant
import com.jiomeet.core.utils.BaseUrl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CoreApplication().recreateModules(this)
        BaseUrl.initializedNetworkInformation(this,Constant.Environment.PROD,"pass useragent")
        setTheme(R.style.Theme_SAMPLE_APP)
    }
}