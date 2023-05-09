package com.example.toncontest.ui.theme.components.main.transaction.send

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
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun ContinueButton(navController: NavController, error: (Boolean) -> Unit, route: String, mode: Int = 0) {
    //todo: check address + circular indicator
    val text = when (mode){
        1 -> MainStrings.confirmButtonText
        2 -> MainStrings.sendInProcessButtonText
        else -> MainStrings.continueButtonText
    }
    Button(
        onClick = {
            when (mode) {
                1 -> navController.navigate(route)
                2 -> navController.popBackStack(route = "main", inclusive = false, saveState = false)
                else -> navController.navigate(route)
            }
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Light_Blue, contentColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Text(
            text = text,
            fontFamily = robotoFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            textAlign = TextAlign.Center
        )
    }
}

