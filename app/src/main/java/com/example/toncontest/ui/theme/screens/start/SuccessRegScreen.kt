package com.example.toncontest.ui.theme.screens.start

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import androidx.navigation.NavController
import com.example.toncontest.data.Data
import com.example.toncontest.data.start.Biometric
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.BackgroundButton
import com.example.toncontest.ui.theme.screens.Loader
import com.example.toncontest.ui.theme.screens.NavBack

@Composable
fun SuccessScreen(navController: NavController, context: Context) {
    var checked by remember { mutableStateOf(false) }
    val sharedPref = context.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()
    //UI
    Scaffold(
        topBar = { NavBack(navController = navController) },
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Loader(R.raw.success)
            Text(
                text = Data.successHeaderText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = robotoFamily,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
            )
            Text(
                text = Data.successMainText,
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
            var checkAlpha = if (Biometric.status(LocalContext.current)) 1f else 0f
            Row(modifier = Modifier.padding(bottom = 20.dp).alpha(checkAlpha)) {
                Checkbox(
                    checked = checked,
                    //TODO: make it work
                    onCheckedChange = { it ->
                        checked = it
                        editor.putBoolean("BIOMETRIC", it)
                        editor.apply()
                    },
                    modifier = Modifier
                        .width(18.dp)
                        .height(18.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = Light_Blue,
                        uncheckedColor = Color.LightGray,
                        checkmarkColor = Color.White
                    )
                )
                Text(
                    text = Data.checkBoxText,
                    modifier = Modifier.padding(start = 12.dp).clickable { checked = !checked },
                    fontFamily = robotoFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            BackgroundButton(
                text = Data.successButtonText,
                backColor = Light_Blue,
                route = "passcode",
                navController = navController
            )
            Spacer(modifier = Modifier.height(108.dp))
        }

    }
}
