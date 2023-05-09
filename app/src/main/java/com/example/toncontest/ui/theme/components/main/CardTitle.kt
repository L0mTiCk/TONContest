package com.example.toncontest.ui.theme.components.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun CardTitle(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontFamily = robotoFamily,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 16.dp)
    )
}