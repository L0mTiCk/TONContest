package com.example.toncontest.ui.theme.screens.main

import TopBarConnection
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import com.example.toncontest.R
import com.example.toncontest.data.main.cardList
import com.example.toncontest.data.ton.account.Account
import com.example.toncontest.data.ton.account.getAccount
import com.example.toncontest.ui.theme.components.main.CreatedWallet
import com.example.toncontest.ui.theme.components.main.ReceiveButton
import com.example.toncontest.ui.theme.components.main.SendButton
import com.example.toncontest.ui.theme.components.main.topbar.main.TopBarBalance
import com.example.toncontest.ui.theme.components.main.transaction.TransactionCard
import com.example.toncontest.ui.theme.components.main.transaction.TransactionColumn
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.Loader
import com.example.toncontest.ui.theme.screens.main.receive.ReceiveCard
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.ton.block.Coins
import org.ton.block.VarUInteger

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(navController: NavController, context: Context, isReceive: Boolean = false) {
    //Data.isFirstLaunch = false
    var isLoaded by remember { mutableStateOf(false) }
    var isAppeared by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(false) }
    var isEmpty by remember { mutableStateOf(false) }
    var hasConnection by remember { mutableStateOf(true) }
    val alpha by animateFloatAsState(targetValue = if (isExpanded) 1f else 0f)
    val connectionAlpha by animateFloatAsState(targetValue = if (!hasConnection) 1f else 0f)
    val max = 300.dp
    val min = 0.dp
    val (minPx, maxPx) = with(LocalDensity.current) { min to max }
    var expandedHeight by remember { mutableStateOf(300.dp) }
    val expandDp by animateDpAsState(targetValue = if (isExpanded) 0.dp else expandedHeight)
    var isRefresh by remember { mutableStateOf(true) }
    var refreshing by remember { mutableStateOf(false) }
    val pullRefreshState =
        rememberPullRefreshState(refreshing = refreshing, onRefresh = { refreshing = true })

    var isReceive by remember { mutableStateOf(isReceive) }
    var transactionCardId by remember { mutableStateOf("") }
    var showTransactionCard by remember { mutableStateOf(false) }

    var balance by remember { mutableStateOf(0.0) }
    var balanceStr = balance.toString().split('.')
    var transactions by remember { mutableStateOf(cardList) }

    val scanQrCodeLauncher = rememberLauncherForActivityResult(ScanCustomCode()) { result ->
        when (result) {
            is QRResult.QRSuccess -> {
                navController.navigate("sendStart/${result.content.rawValue}")
            }

            is QRResult.QRMissingPermission -> {
                navController.navigate("noCamera")
            }

            else -> null
        }
    }

    var account by remember {
        mutableStateOf(
            Account(
                "WasdWasdWasdWasdWasdWasdWasdWasdWasdWasdWasdWasd",
                Coins(VarUInteger(0))
            )
        )
    }

    //test
    LaunchedEffect(key1 = Unit) {
        delay(10000)
        hasConnection = false
    }

    LaunchedEffect(key1 = isRefresh, key2 = refreshing) {
        if (isRefresh || refreshing) {
            CoroutineScope(Dispatchers.IO).launch {
                account = getAccount(context = context)
                Log.d("Balance", account.balance.toString())
                balance = if (account.balance.toString().toDouble() > 0.0) {
                    account.balance.toString().toDouble()
                } else {
                    0.0
                }
                balanceStr = account.balance.toString().split(".")
                if (cardList.isEmpty()) {
                    isEmpty = true
                } else {
                    transactions = cardList
                }
                isLoaded = true
                delay(120)
                isAppeared = true
                isRefresh = false
                refreshing = false
            }
        }
    }

    Box(Modifier.pullRefresh(pullRefreshState)) {
        Scaffold(
            backgroundColor = Color.Black,

            topBar = {
                if (hasConnection) {
                    TopBarBalance(balance = balance, alpha = alpha)
                } else {
                    TopBarConnection(alpha = connectionAlpha, updated = { hasConnection = it })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            scanQrCodeLauncher.launch(
                                ScannerConfig.build {
                                    setBarcodeFormats(listOf(BarcodeFormat.FORMAT_ALL_FORMATS)) // set interested barcode formats
                                    setHapticSuccessFeedback(true) // enable (default) or disable haptic feedback when a barcode was detected
                                    setShowTorchToggle(true) // show or hide (default) torch/flashlight toggle button
                                    setShowCloseButton(true) // show or hide (default) close button
                                    setHorizontalFrameRatio(1f) // set the horizontal overlay ratio (default is 1 / square frame)
                                    setUseFrontCamera(false) // use the front camera
                                })
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
                                text = account.address.substring(
                                    0,
                                    6
                                ) + "..." + account.address.substring(account.address.length - 6),
                                fontFamily = robotoFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Color.White,
                                modifier = Modifier
                                    .alpha(
                                        animateFloatAsState(targetValue = if (isAppeared) 1f else 0f).value
                                    )
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
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.White,
                                                fontSize = 44.sp
                                            )
                                        ) {
                                            append(balanceStr[0])
                                        }
                                        withStyle(
                                            style = SpanStyle(
                                                color = Color.White,
                                                fontSize = 32.sp
                                            )
                                        ) {
                                            append(".${balanceStr[1]}")
                                        }
                                    },
                                    fontFamily = robotoFamily,
                                    fontSize = 44.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .alpha(
                                            animateFloatAsState(targetValue = if (isAppeared) 1f else 0f).value
                                        )
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
                                ReceiveButton(onClick = { newReceive -> isReceive = newReceive })
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
                                TransactionColumn(cards = transactions, returnId = { id ->
                                    transactionCardId = id!!
                                    showTransactionCard = true
                                }
                                )
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
                    ReceiveCard(
                        onDrag = { newReceive -> isReceive = newReceive },
                        context = context
                    )
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
                    TransactionCard(
                        onDrag = { newReceive -> showTransactionCard = newReceive },
                        transactionCardId,
                        navController = navController
                    )
                }
            }
        )
        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}
