package com.example.toncontest.ui.theme.screens.main.receive

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.toncontest.R

@Composable
fun ShareQR() {
    //TODO: change to generated QR
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.mipmap.qr),
            contentDescription = "Temp QR",
            modifier = Modifier
                .size(160.dp)
        )
    }
}