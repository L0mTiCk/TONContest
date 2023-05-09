package com.example.toncontest.ui.theme.components.main.transaction.send.done

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.Loader

@Composable
fun SendingScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Loader(res = R.raw.send)
        Text(
            text = MainStrings.sendInProcessHeader,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = robotoFamily,
            modifier = Modifier
                .padding(top = 12.dp, end = 12.dp)
        )
        Text(
            text = MainStrings.sendInProcessMain,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = robotoFamily,
            textAlign = TextAlign.Center
        )
    }
}