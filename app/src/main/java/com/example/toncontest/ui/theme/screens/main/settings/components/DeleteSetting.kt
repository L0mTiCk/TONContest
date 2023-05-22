package com.example.toncontest.ui.theme.screens.main.settings.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.data.Data
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.TonRed
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun DeleteSetting(navController: NavController, context: Context) {

    var showDialog by remember { mutableStateOf(false) }
    val sharedPreferences = context.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)

    @Composable
    fun DeleteAlert(show: Boolean) {
        var showDialogLocal = remember { mutableStateOf(show) }
        if (showDialogLocal.value) {
            AlertDialog(
                onDismissRequest = { showDialogLocal.value = false; showDialog = false },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                title = { Text(text = Data.deleteWalletAlertTitle) },
                text = { Text(text = Data.deleteWalletAlertText) },
                confirmButton = {
                    Text(text = Data.deleteWalletConfirmButton,
                        color = Light_Blue,
                        fontFamily = robotoFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                showDialogLocal.value = false
                                showDialog = false
                                sharedPreferences
                                    .edit()
                                    .clear()
                                    .apply()
                                navController.navigate("start") {
                                    popUpTo(
                                        "main"
                                    ) {
                                        inclusive = true
                                    }
                                }
                            }
                            .padding(20.dp)
                    )
                },
                dismissButton = {
                    Text(text = Data.deleteWalletCancelButton,
                        color = Light_Blue,
                        fontFamily = robotoFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                showDialogLocal.value = false
                                showDialog = false
                            }
                            .padding(20.dp)
                    )
                }
            )
        }
    }

    if (showDialog) {
        DeleteAlert(show = showDialog)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable {
                    showDialog = true
                }
        ) {
            Text(
                text = MainStrings.deleteSettings,
                fontFamily = robotoFamily,
                color = TonRed,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            )
        }
    }
}