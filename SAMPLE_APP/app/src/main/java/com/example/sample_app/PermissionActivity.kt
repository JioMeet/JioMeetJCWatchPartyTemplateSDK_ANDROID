package com.example.sample_app

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.jio.sdk.utils.extensions.showToast

class PermissionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (HelperClass.checkPermissionForCorporateUsers(this) && HelperClass.isInternetAvailable(
                this
            )
        ) {
            setContent {
                val joinCallIntent = Intent(this, MainActivity::class.java)
                startActivity(joinCallIntent)
            }
        } else {
            HelperClass.requestPermissionForCorporateUsers(this)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            val joinCallIntent = Intent(this, MainActivity::class.java)
            startActivity(joinCallIntent)
        } else {
            showToast("Provide necessary permissions to proceed.")
        }
    }
}