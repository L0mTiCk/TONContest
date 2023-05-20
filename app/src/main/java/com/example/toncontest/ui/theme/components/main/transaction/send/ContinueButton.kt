package com.example.toncontest.ui.theme.components.main.transaction.send

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.data.main.Variables
import com.example.toncontest.data.main.send.sendInfo
import com.example.toncontest.data.ton.account.account
import com.example.toncontest.data.ton.account.checkAccount
import com.example.toncontest.data.ton.transactions.checkAddress
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ContinueButton(navController: NavController, error: (Boolean) -> Unit, route: String, mode: Int = 0, address: String = "") {
    var isChecking by remember { mutableStateOf(false) }
    var isValid by rememberSaveable { mutableStateOf(false) }
    var alpha by remember { mutableStateOf(0f) }
    //todo: circular indicator
    val text = when (mode) {
        1 -> MainStrings.continueButtonText
        2 -> MainStrings.continueButtonText
        3 -> MainStrings.confirmButtonText
        4 -> MainStrings.sendInProcessButtonText
        else -> MainStrings.continueButtonText
    }


    LaunchedEffect(key1 = isChecking) {
        when (mode) {
            1 -> {
                if (isChecking) {
                    CoroutineScope(Dispatchers.IO).launch {
                        alpha = 1f
                        Log.d("checkAcc", "button click")
                        if (checkAccount(sendInfo.recipient))
                            isValid = true
                        else
                            error(true)
                    }
                    alpha = 0f
                }
            }
        }
        isChecking = false
    }

    LaunchedEffect(key1 = isValid) {
        if (isValid) {
            navController.navigate(route)
            isValid = false
        }
    }

    Button(
        onClick = {
            when (mode) {
                1 -> {
                    if (checkAddress()) {
                        isChecking = true
                    } else {
                        error(true)
                    }
                }

                2 -> {
                    if (sendInfo.amount <= account.balance.toString()
                            .toDouble() && sendInfo.amount > 0.0
                    )
                        navController.navigate(route)
                }

                3 -> {
                    if (sendInfo.comment.length <= Variables.sendConfirmInputLen) {
                        navController.navigate(route) {
                            popUpTo("sendFinal") {
                                inclusive = false
                            }
                        }
                    }
                }

                4 -> {
                    navController.navigate(route) {
                        popUpTo("main") {
                            inclusive = true
                        }
                    }
                }

                else -> {
                    navController.navigate(route)
                }
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
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = text,
            fontFamily = robotoFamily,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 14.dp),
            textAlign = TextAlign.Center
        )
        CircularProgressIndicator(
            color = Color.White,
            strokeWidth = 2.dp,
            modifier = Modifier
                .size(24.dp)
                .alpha(alpha)
        )
    }
}

