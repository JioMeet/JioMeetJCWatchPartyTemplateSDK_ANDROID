package com.example.sample_app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jio.sdksampleapp.view.composables.ui.theme.background
import org.jio.sdksampleapp.view.composables.ui.theme.buttonBackground

@Composable
fun CoreLoginView(onJoinMeetingClick: () -> Unit) {
    Surface(color = background) {
        Box(modifier = Modifier.fillMaxSize()) {
                Button(
                    onClick = {
                            onJoinMeetingClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                        .height(40.dp)
                        .alpha(0.5f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = buttonBackground,
                        contentColor = Color.White,
                        disabledBackgroundColor = buttonBackground.copy(alpha = 0.5f)
                    )
                ) {
                    Text(
                        text = "Start",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W900,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
