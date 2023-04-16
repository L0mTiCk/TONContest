package com.example.toncontest.ui.theme.screens.start

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.toncontest.R
import com.example.toncontest.data.Data
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.BackgroundButton
import com.example.toncontest.ui.theme.screens.Loader

@Composable
fun NoMnemonicScreen(navController: NavController){
    Column(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(174.dp))
            Loader(R.raw.toobad)
            Text(
                text = Data.noMnemonicHeaderText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = robotoFamily,
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
            )
            Text(
                text = Data.noMnemonicMainText,
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
                text = Data.noMnemonicImportButtonText,
                backColor = Light_Blue,
                route = "import",
                navController = navController
            )
            Spacer(modifier = Modifier.height(8.dp))
            BackgroundButton(
                text = Data.noMnemonicCreateButtonText,
                backColor = Color.White,
                textColor = Light_Blue,
                route = "congrats",
                navController = navController
            )
            Spacer(modifier = Modifier.height(45.dp))
        }
    }
}