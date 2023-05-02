package com.example.toncontest.ui.theme.screens.main.receive

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue

@Composable
fun ShareButton(address: String) {
    val context = LocalContext.current
    Button(
        onClick = {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, address)
            }
            context.startActivity(Intent.createChooser(shareIntent, null))
        },
        modifier = Modifier
            .padding(
                start = 16.dp,
                top = 28.dp,
                end = 16.dp,
                bottom = 16.dp
            )
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Light_Blue),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = MainStrings.shareButtonText,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 14.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}