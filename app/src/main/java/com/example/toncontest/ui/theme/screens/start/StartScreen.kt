package com.example.toncontest

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.BackgroundButton
import com.example.toncontest.ui.theme.screens.Loader

val startText = "TON Wallet allows you to make fast and\n" +
        "secure blockchain-based payments\n" +
        "without intermediaries."

@Composable
fun StartScreen(navController: NavController){
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(174.dp))
            Loader(R.raw.start)
            Text(
                text = "TON Wallet",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = robotoFamily,
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
            )
            Text(
                text = startText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = robotoFamily,
                textAlign = TextAlign.Center
            )
        }
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            BackgroundButton(
                text = "Create my wallet",
                backColor = Light_Blue,
                route = "congrats",
                navController = navController
            )
            BackgroundButton(
                text = "Import existing wallet",
                backColor = Color.White,
                textColor = Light_Blue,
                route = "start",
                navController = navController
            )
            Spacer(modifier = Modifier.height(45.dp))
        }
    }

}

