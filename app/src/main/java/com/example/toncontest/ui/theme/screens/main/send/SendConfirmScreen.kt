package com.example.toncontest.ui.theme.screens.main.send

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.data.main.Variables
import com.example.toncontest.data.main.send.sendInfo
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.TonGray
import com.example.toncontest.ui.theme.TonOrange
import com.example.toncontest.ui.theme.TonPink
import com.example.toncontest.ui.theme.TonRed
import com.example.toncontest.ui.theme.components.main.transaction.details.DetailsAddressField
import com.example.toncontest.ui.theme.components.main.transaction.send.ContinueButton
import com.example.toncontest.ui.theme.components.main.transaction.send.SendCardHeader
import com.example.toncontest.ui.theme.components.main.transaction.send.confirm.SendDetailsAmount
import com.example.toncontest.ui.theme.components.main.transaction.send.confirm.SendDetailsFee
import com.example.toncontest.ui.theme.robotoFamily


@Composable
fun SendConfirmScreen(navController: NavController) {
    var comment by remember { mutableStateOf("") }
    var isOverflowed by remember { mutableStateOf(false) }
    fun buildAnnotatedStringWithColors(text: String): AnnotatedString {
        val builder = AnnotatedString.Builder()
        if (text.length <= Variables.sendConfirmInputLen) {
            builder.withStyle(
                SpanStyle(
                    color = Color.Black,
                    fontFamily = robotoFamily
                )
            ) {
                append(text)
            }
        } else {
            val nonColoredSubstr = text.substring(0, Variables.sendConfirmInputLen)
            val coloredSubstr = text.substring(Variables.sendConfirmInputLen)
            builder.withStyle(
                SpanStyle(
                    color = Color.Black,
                    fontFamily = robotoFamily
                )
            ) {
                append(nonColoredSubstr)
            }
            if (coloredSubstr.isNotEmpty()) {
                builder.withStyle(
                    SpanStyle(
                        color = TonRed,
                        background = TonPink,
                        fontFamily = robotoFamily
                    )
                ) {
                    append(coloredSubstr)
                }
            }
        }
        return builder.toAnnotatedString()
    }

    LaunchedEffect(key1 = comment) {
        isOverflowed = comment.length > Variables.sendConfirmInputLen
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
                .fillMaxHeight()
        ) {
            SendCardHeader(navController = navController)
            Column(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxHeight()
                    .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
            ) {
                Text(
                    text = MainStrings.sendConfirmSubtitle,
                    color = Light_Blue,
                    fontFamily = robotoFamily,
                    fontSize = 15.sp,
                    modifier = Modifier
                )
                TextField(
                    value = comment,
                    onValueChange = { it ->
                        comment = it
                    },
                    maxLines = 5,
                    visualTransformation = {
                        TransformedText(
                            buildAnnotatedStringWithColors(comment),
                            offsetMapping = OffsetMapping.Identity
                        )
                    },
                    placeholder = {
                        Text(
                            text = MainStrings.sendConfirmPlaceholder,
                            color = TonGray,
                            fontFamily = robotoFamily
                        )
                    },
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Light_Blue,
                        cursorColor = Light_Blue,
                        backgroundColor = Color.Transparent,
                    )
                )
                Text(
                    text = MainStrings.sendConfirmHint,
                    color = TonGray,
                    fontFamily = robotoFamily,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(top = 12.dp)
                )
                Text(
                    text = if (isOverflowed) MainStrings.noCharacterLeft(comment.length) else MainStrings.someCharacterLeft(
                        comment.length
                    ),
                    modifier = Modifier
                        .alpha(
                            animateFloatAsState(targetValue = if (comment.isNotEmpty()) 1f else 0f).value
                        ),
                    color = if (isOverflowed) TonRed else TonOrange,
                    fontSize = 13.sp,
                    fontFamily = robotoFamily
                )
                Text(
                    text = MainStrings.transactionDetailsHeader,
                    color = Light_Blue,
                    fontFamily = robotoFamily,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .padding(top = 32.dp, start = 20.dp)
                        .fillMaxWidth()
                )
                DetailsAddressField(addressType = MainStrings.transactionCardRecipientTitle, address = sendInfo.recipient)
                SendDetailsAmount()
                SendDetailsFee()
                Spacer(modifier = Modifier.weight(1f))
                ContinueButton(navController = navController, error = {}, route = "sendPending", mode = 1)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
