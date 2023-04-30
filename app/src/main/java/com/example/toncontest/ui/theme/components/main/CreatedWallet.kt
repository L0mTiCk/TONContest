package com.example.toncontest.ui.theme.components.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun CreatedWallet(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoaderOnce(res = R.raw.walletcreated)
        Text(
            text = MainStrings.walletCreatedMain,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 12.dp)
        )
        Text(
            text = MainStrings.walletCreatedSubtitle,
            color = Color.LightGray,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 20.dp)
        )
        val address = "UQBFz01R2CU7YA8pevUaNIYEzi1mRo4cX-r3W2Dwx-WEAoKP"
        Text(
            text = address,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 6.dp, start = 24.dp, end = 24.dp).width(225.dp)
        )
    }
}