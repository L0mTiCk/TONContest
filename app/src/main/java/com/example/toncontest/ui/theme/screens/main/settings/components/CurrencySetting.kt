package com.example.toncontest.ui.theme.screens.main.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun CurrencySetting() {
    var expanded by remember { mutableStateOf(false) }
    var xPosition by remember { mutableStateOf(0.dp) }
    var yPosition by remember { mutableStateOf(0.dp) }
    var value by remember { mutableStateOf("USD") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = MainStrings.currencySettings,
                fontFamily = robotoFamily,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 14.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset(
                        x = xPosition,
                        y = yPosition
                    )
                ) {
                    DropdownMenuItem(onClick = {
                        expanded = false
                        value = "BYN"
                    }) {
                        Text(
                            text = "BYN",
                            fontSize = 15.sp,
                            color = Light_Blue
                        )
                    }
                    DropdownMenuItem(onClick = {
                        expanded = false
                        value = "USD"
                    }) {
                        Text(
                            text = "USD",
                            fontSize = 15.sp,
                            color = Light_Blue
                        )
                    }
                    DropdownMenuItem(onClick = {
                        expanded = false
                        value = "RUB"
                    }) {
                        Text(
                            text = "RUB",
                            fontSize = 15.sp,
                            color = Light_Blue
                        )
                    }
                }
                Text(
                    text = value,
                    fontSize = 15.sp,
                    color = Light_Blue,
                    modifier = Modifier
                        .clickable {
                            expanded = true
                        }
                        .onGloballyPositioned {
                            xPosition = it.localToRoot(Offset.Zero).x.dp
                            yPosition = it.localToRoot(Offset.Zero).y.dp
                        }
                )
            }

        }
        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier.fillMaxWidth()
        )
    }
}