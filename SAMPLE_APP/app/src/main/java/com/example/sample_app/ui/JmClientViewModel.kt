package com.example.sample_app.ui

import androidx.lifecycle.ViewModel
import com.jiomeet.core.main.JMClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JMClientViewModel @Inject constructor(private val jmClient: JMClient) : ViewModel() {
    init {
        jmClient.init()
        // Perform other setup operations here if needed
    }
}
