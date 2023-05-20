package com.example.toncontest.ui.theme.screens.main.send

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.toncontest.data.ton.transactions.sendTransaction
import com.example.toncontest.ui.theme.components.main.transaction.send.ContinueButton
import com.example.toncontest.ui.theme.components.main.transaction.send.done.SendingDoneScreen
import com.example.toncontest.ui.theme.components.main.transaction.send.done.SendingScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

@Composable
fun SendFinalScreen(navController: NavController) {
    var isSent by remember { mutableStateOf(true) }
    var isDone by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        CoroutineScope(Dispatchers.IO).async {
            sendTransaction()
        }.await()
        isSent = false
        delay(300)
        isDone = true
    }

    Column(
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
                .padding(top = 2.dp, start = 20.dp, end = 20.dp)
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack(route = "main", inclusive = false, saveState = false)
                },
                modifier = Modifier
                    .offset(x = (-20).dp)
            ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "Cross")
            }
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = isSent,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                SendingScreen()
            }
            AnimatedVisibility(
                visible = isDone,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                SendingDoneScreen()
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Bottom
            ) {
                ContinueButton(navController = navController, error = {}, route = "main", mode = 4)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}