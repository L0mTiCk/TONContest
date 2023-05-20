package com.example.toncontest.ui.theme.components.main.transaction.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue

@Composable
fun SendToAddressButton(address: String, navController: NavController) {
    Button(
        onClick = {
            navController.navigate("sendStart/${address}")
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
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = MainStrings.transactionSendButtonTitle,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 14.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}