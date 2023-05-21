package com.example.toncontest.ui.theme.screens.main.settings

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.screens.main.settings.components.AddressSetting
import com.example.toncontest.ui.theme.screens.main.settings.components.BiometricSetting
import com.example.toncontest.ui.theme.screens.main.settings.components.CurrencySetting
import com.example.toncontest.ui.theme.screens.main.settings.components.DeleteSetting
import com.example.toncontest.ui.theme.screens.main.settings.components.ListOfTokens
import com.example.toncontest.ui.theme.screens.main.settings.components.PasscodeSetting
import com.example.toncontest.ui.theme.screens.main.settings.components.ShowRecovery

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SettingsColumn(navController: NavController, context: Context) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxSize()
    ) {
        stickyHeader {
            Text(
                text = MainStrings.generalSettings,
                fontSize = 15.sp,
                color = Light_Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 4.dp)
            )
        }
        item {
            NotificationSetting()
        }
        item {
            AddressSetting()
        }
        item {
            CurrencySetting(context = context)
        }
        item {
            ListOfTokens()
        }
        stickyHeader {
            Text(
                text = MainStrings.securitySettings,
                fontSize = 15.sp,
                color = Light_Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 4.dp, top = 20.dp)
            )
        }
        item {
            ShowRecovery(navController = navController)
        }
        item {
            PasscodeSetting(navController = navController)
        }
        item {
            BiometricSetting(context = context)
        }
        item {
            DeleteSetting(navController = navController)
        }
    }
}