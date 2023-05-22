package com.example.toncontest.ui.theme.components.main.topbar.main

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import com.example.toncontest.data.main.network.values
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.Loader
import kotlinx.coroutines.delay

@Composable
fun TopBarBalance(balance: Double, alpha: Float, context: Context) {

    val sharedPref = context.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)
    var currencyName = sharedPref.getString("CURRENCY", "usd")?.lowercase()

    var symbol: String
    when (currencyName) {
        "usd" -> symbol = "$"
        "rub" -> symbol = "₽"
        "eur" -> symbol = "€"
        else -> symbol = ""
    }

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        delay(300)
        isVisible = true
    }
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .alpha(alpha)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, top = 7.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Loader(
                        R.raw.start, modifier = Modifier
                            .width(14.dp)
                            .height(14.dp)
                    )
                    Text(
                        text = balance.toString(),
                        fontFamily = robotoFamily,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
                Text(
                    text = "≈ ${symbol}${"%.${2}f".format(values.getValue(balance, currencyName!!))}",
                    fontFamily = robotoFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.LightGray,
                    modifier = Modifier.padding(start = 2.dp)
                )
            }
        }
    }
}