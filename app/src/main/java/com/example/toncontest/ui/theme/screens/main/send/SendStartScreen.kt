package com.example.toncontest.ui.theme.screens.main.send

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.data.main.cardList
import com.example.toncontest.data.main.send.sendInfo
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.TonGray
import com.example.toncontest.ui.theme.components.main.CardTitle
import com.example.toncontest.ui.theme.components.main.transaction.send.ContinueButton
import com.example.toncontest.ui.theme.components.main.transaction.send.InvalidAddressPopUp
import com.example.toncontest.ui.theme.components.main.transaction.send.PasteButton
import com.example.toncontest.ui.theme.components.main.transaction.send.RecentTransaction
import com.example.toncontest.ui.theme.components.main.transaction.send.ScanButton
import com.example.toncontest.ui.theme.robotoFamily
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SendStartScreen(navController: NavController ,address: String = "", id: Int = 0) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var isError by remember { mutableStateOf(false) }
    var visibility by remember { mutableStateOf(false) }
    var sendAddress by remember { mutableStateOf(address) }
    var hasRecent by remember { mutableStateOf(cardList.isNotEmpty()) }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
        delay(5000)
    }

    LaunchedEffect(isError) {
        if (isError) {
            visibility = true
            delay(4500)
            visibility = false
            isError = false
        }
    }

    LaunchedEffect(key1 = sendAddress) {
        sendInfo.recipient = sendAddress
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .padding(top = 2.dp)
                .fillMaxHeight()
        ) {
            CardTitle(text = MainStrings.sendScreenTitle)
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = MainStrings.sendStartSubtitle,
                    color = Light_Blue,
                    fontFamily = robotoFamily,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                TextField(
                    value = sendAddress,
                    onValueChange = {
                        sendAddress = it
                    },
                    placeholder = {
                        Text(
                            text = MainStrings.sendStartPlaceholder,
                            color = TonGray,
                            fontFamily = robotoFamily
                        )
                    },
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .heightIn(min = 65.dp)
                        .focusRequester(focusRequester),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Light_Blue,
                        cursorColor = Light_Blue,
                        backgroundColor = Color.Transparent,

                        )
                )
                Text(
                    text = MainStrings.sendHintText,
                    color = TonGray,
                    fontFamily = robotoFamily,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(top = 12.dp)
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    PasteButton(paste = { newAddress -> sendAddress = newAddress })
                    Spacer(modifier = Modifier.width(8.dp))
                    ScanButton(navController = navController, {newAddress -> sendAddress = newAddress})
                }
                if (hasRecent) {
                    Text(
                        text = MainStrings.recentListHeader,
                        color = Light_Blue,
                        fontFamily = robotoFamily,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(top = 32.dp)
                    )
                    RecentTransaction(address = { recentAddress ->
                        sendAddress = recentAddress
                    })
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            ContinueButton(
                navController = navController,
                { error -> isError = error },
                "sendAmount",
                1
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            AnimatedVisibility(
                visible = visibility
            ) {
                InvalidAddressPopUp()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}