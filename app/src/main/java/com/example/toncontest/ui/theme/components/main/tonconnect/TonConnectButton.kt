package com.example.toncontest.ui.theme.components.main.tonconnect

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue

@Composable
fun ConnectButton() {
    var isConnected by remember { mutableStateOf(false) }
    var isConnecting by remember { mutableStateOf(false) }
    Button(
        onClick = {
            isConnecting = true
        },
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 24.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Light_Blue),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
    ) {
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = MainStrings.tonConnectButton,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 14.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        CircularProgressIndicator(
            color = Color.White,
            strokeWidth = 2.dp,
            modifier = Modifier
                .size(24.dp)
                .alpha(animateFloatAsState(targetValue = if (isConnecting) 1f else 0f).value)
        )
    }
}