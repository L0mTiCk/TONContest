package com.example.toncontest.ui.theme.screens.main.settings.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun BiometricSetting(context: Context) {
    val sharedPref = context.getSharedPreferences("MY_APP_PREFERENCES", Context.MODE_PRIVATE)
    val editor = sharedPref.edit()

    var checked by remember { mutableStateOf(sharedPref.getBoolean("BIOMETRIC", false)) }
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
                text = MainStrings.biometricSettings,
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
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        editor.putBoolean("BIOMETRIC", it)
                        editor.apply()
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Light_Blue,
                        checkedTrackColor = Light_Blue,
                        checkedTrackAlpha = 0.8f,
                        uncheckedThumbColor = Color.LightGray,
                        uncheckedTrackColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .width(32.dp)
                        .height(14.dp)
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