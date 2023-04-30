package com.example.toncontest.ui.theme.screens.main.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.TonRed
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun DeleteSetting(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable {
                    //TODO: delete wallet fun
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