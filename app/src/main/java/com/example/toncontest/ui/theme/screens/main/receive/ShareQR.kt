package com.example.toncontest.ui.theme.screens.main.receive

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShareQR(context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            //bitmap = generateQRCodeWithLogo(walletV4R2.address.toString(userFriendly = true), context = context),
            bitmap = test(context = context),
            contentDescription = "Temp QR",
            modifier = Modifier
                .size(160.dp)
        )
    }
}