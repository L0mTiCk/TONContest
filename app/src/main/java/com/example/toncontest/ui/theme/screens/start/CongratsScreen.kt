package com.example.toncontest

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.data.Data
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.BackgroundButton
import com.example.toncontest.ui.theme.screens.Loader
import com.example.toncontest.ui.theme.screens.NavBack



@Composable
fun CongratsScreen(navController: NavController) {
    //UI
    Scaffold(topBar = { NavBack(navController = navController)},
    backgroundColor = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Loader(R.raw.congratulations)
            Text(
                text = Data.congratsHeaderText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = robotoFamily,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
            )
            Text(
                text = Data.congratsMainText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = robotoFamily,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(280.dp)
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
                text = "Proceed",
                backColor = Light_Blue,
                route = "recovery",
                navController = navController
            )
            Spacer(modifier = Modifier.height(108.dp))
        }

    }
}