package com.example.toncontest.ui.theme.screens.main

import TopBarConnection
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.ui.theme.screens.Loader
import com.example.toncontest.R
import com.example.toncontest.data.Data
import com.example.toncontest.ui.theme.components.main.CreatedWallet
import com.example.toncontest.ui.theme.components.main.ReceiveButton
import com.example.toncontest.ui.theme.components.main.SendButton
import com.example.toncontest.ui.theme.components.main.transaction.TransactionCard
import com.example.toncontest.ui.theme.components.main.transaction.TransactionColumn
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.main.receive.ReceiveCard
import com.example.toncontest.ui.theme.components.main.topbar.main.TopBarBalance
import kotlinx.coroutines.delay

@Composable
fun MainScreen(navController: NavController) {
    Data.isFirstLaunch = false
    var isLoaded by remember { mutableStateOf(false)}
    var isAppeared by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    var isEmpty by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(targetValue = if (isExpanded) 1f else 0f)
    val max = 300.dp
    val min = 0.dp
    val (minPx, maxPx) = with(LocalDensity.current) {min to max}
    var expandedHeight by remember { mutableStateOf(300.dp) }
    val expandDp by animateDpAsState(targetValue = if(isExpanded) 0.dp else expandedHeight)

    var isReceive by remember { mutableStateOf(false) }
    var transactionCardId by remember { mutableStateOf(0) }
    var showTransactionCard by remember { mutableStateOf(false) }

    var balance = 52.0
    var balanceStr = balance.toString().split('.')

    var hasConnection by remember { mutableStateOf(true) }


    //test
    LaunchedEffect(key1 = Unit) {
        delay(10000)
        hasConnection = false
    }

    Scaffold(
        backgroundColor = Color.Black,

        topBar = {
                if (hasConnection) {
                    TopBarBalance(balance = balance, alpha = alpha)
                } else {
                    TopBarConnection(alpha = alpha, updated = { hasConnection = it})
                }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.End
            ) {
                //TODO: link onclick func
                IconButton(
                    onClick = {
                        /*TODO*/
                        navController.navigate("login")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.scan),
                        contentDescription = "Scan QR",
                        tint = Color.White
                    )
                }
                IconButton(
                    modifier = Modifier.padding(end = 12.dp),
                    onClick = { navController.navigate("settings") }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = "Settings",
                        tint = Color.White
                    )
                }
            }
        },

        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(1 - alpha)
                        .height(expandDp)
                        .clickable(
                            onClick = {
                                isReceive = false
                                showTransactionCard = false
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ),

                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            //TODO: change to wallet address
                            text = "Wallet address",
                            fontFamily = robotoFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        )
                        Row(
                            modifier = Modifier.height(56.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Loader(
                                res = R.raw.start, modifier = Modifier
                                    .width(44.dp)
                                    .height(44.dp)
                            )
                            Text(
                                buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color.White, fontSize = 44.sp)) {
                                        append(balanceStr[0])
                                    }
                                    withStyle(style = SpanStyle(color = Color.White, fontSize = 32.sp)) {
                                        append(".${balanceStr[1]}")
                                    }
                                },
                                fontFamily = robotoFamily,
                                fontSize = 44.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
//                        verticalArrangement = Arrangement.Bottom,
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ReceiveButton(onClick = {newReceive -> isReceive = newReceive})
                            Spacer(modifier = Modifier.width(12.dp))
                            SendButton(navController = navController, isLoaded = isLoaded)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .draggable(
                            orientation = Orientation.Vertical,
                            state = rememberDraggableState { delta ->
                                val newValue = expandedHeight + delta.dp
                                expandedHeight = newValue.coerceIn(minPx, maxPx)
                            },
                            onDragStopped = { velocity ->
                                if (velocity < 0) {
                                    expandedHeight = 0.dp
                                    isExpanded = true
                                } else {
                                    expandedHeight = 300.dp
                                    isExpanded = false
                                }
                            }
                        )
                        .clickable(
                            onClick = {
                                if (isExpanded)
                                    expandedHeight = 300.dp
                                isExpanded = !isExpanded
                            },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        )
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LaunchedEffect(key1 = Unit) {
                        delay(1000)
                        isLoaded = true
                        delay(120)
                        isAppeared = true
                    }
                    AnimatedVisibility(
                        visible = !isLoaded,
                        enter = fadeIn(
                            animationSpec = tween(
                                2500,
                                easing = FastOutSlowInEasing
                            )
                        ),
                        exit = fadeOut(animationSpec = tween(100, easing = FastOutSlowInEasing))
                    ) {
                        Loader(res = R.raw.waiting)
                    }
                    AnimatedVisibility(
                        visible = isAppeared,
                        enter = fadeIn(
                            animationSpec = tween(
                                1000,
                                easing = FastOutSlowInEasing
                            )
                        ),
                        exit = fadeOut(animationSpec = tween(500, easing = FastOutSlowInEasing))
                    ) {
                        if (isEmpty)
                            CreatedWallet()
                        else
                            TransactionColumn() { id ->
                                transactionCardId = id!!
                                showTransactionCard = true
                            }
                    }
                }
            }
            AnimatedVisibility(
                visible = isReceive && isLoaded,
                enter = slideInVertically(
                    initialOffsetY = { 1300 }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { 1500 }
                )
            ) {
                ReceiveCard(onDrag = {newReceive -> isReceive = newReceive})
            }
            AnimatedVisibility(
                visible = showTransactionCard,
                enter = slideInVertically(
                    initialOffsetY = { 1300 }
                ),
                exit = slideOutVertically(
                    targetOffsetY = { 1900 }
                )
            ) {
                expandedHeight = 300.dp
                isExpanded = false
                TransactionCard(onDrag = {newReceive -> showTransactionCard = newReceive}, transactionCardId - 1)
            }
        }
    )
}