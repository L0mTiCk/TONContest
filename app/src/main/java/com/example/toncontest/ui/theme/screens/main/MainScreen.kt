package com.example.toncontest.ui.theme.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.ui.theme.screens.Loader
import com.example.toncontest.R
import com.example.toncontest.ui.theme.components.main.CreatedWallet
import com.example.toncontest.ui.theme.components.main.ReceiveButton
import com.example.toncontest.ui.theme.components.main.SendButton
import com.example.toncontest.ui.theme.robotoFamily
import kotlinx.coroutines.delay

@Composable
fun MainScreen() {
    var isLoaded by remember { mutableStateOf(false)}
    var isAppered by remember { mutableStateOf(false) }
    val loaderAlpha by animateFloatAsState( if(isLoaded) 0f else 1f )

    var balance = 56.2322
    var balanceStr = balance.toString().split('.')

    Scaffold(
        backgroundColor = Color.Black,

        topBar = {
            Row(modifier = Modifier
                .height(56.dp)
                .alpha(1f)) {
                Column(modifier = Modifier.padding(start = 16.dp, top = 7.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Loader(R.raw.start, modifier = Modifier
                            .width(14.dp)
                            .height(14.dp))
                        Text(
                            text = balance.toString(),
                            fontFamily = robotoFamily,
                            fontSize =18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                    Text(
                        text = "≈ \$89.6",
                        fontFamily = robotoFamily,
                        fontSize =14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.End
            ) {
                //TODO: link onclick func
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.scan),
                        contentDescription = "Scan QR",
                        tint = Color.White
                    )
                }
                IconButton(
                    modifier = Modifier.padding(end = 12.dp),
                    onClick = { /*TODO*/ }
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
                        .height(300.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
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
                            ReceiveButton()
                            Spacer(modifier = Modifier.width(12.dp))
                            SendButton()
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.White, shape = RoundedCornerShape(12.dp)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LaunchedEffect(key1 = Unit) {
                        delay(5000)
                        isLoaded = true
                        delay(120)
                        isAppered = true
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
                        visible = isAppered,
                        enter = fadeIn(
                            animationSpec = tween(
                                1000,
                                easing = FastOutSlowInEasing
                            )
                        ),
                        exit = fadeOut(animationSpec = tween(500, easing = FastOutSlowInEasing))
                    ) {
                        CreatedWallet()
                    }
                }
            }
        }
    )
}