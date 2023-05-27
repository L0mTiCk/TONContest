package com.example.toncontest.ui.theme.components.main.tonconnect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.data.ton.account.account
import com.example.toncontest.ui.theme.TonGray
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun TonConnectCard(onDrag: (Boolean) -> Unit) {
    val accAddress = account.address
    val address = accAddress.substring(0, accAddress.length / 2) + "\n" + accAddress.substring(accAddress.length / 2)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                )
                .draggable(
                    state = rememberDraggableState(onDelta = {}),
                    onDragStopped = { velocity ->
                        if (velocity > 0) {
                            onDrag(false)
                        }
                    },
                    orientation = Orientation.Vertical
                ),
            verticalArrangement = Arrangement.Bottom
        ) {
            IconButton(
                onClick = { onDrag(false) },
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close button",
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    Icons.Filled.Settings,
                    contentDescription = "Connect icon",
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = MainStrings.tonConnectTitle,
                    fontSize = 24.sp,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 20.dp)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontFamily = robotoFamily,
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        ) {
                           append(MainStrings.tonConnectMain)
                        }
                        withStyle(
                            SpanStyle(
                                fontFamily = robotoFamily,
                                fontSize = 15.sp,
                                color = TonGray
                            )
                        ) {
                            append(address)
                        }
                        withStyle(
                            SpanStyle(
                                fontFamily = robotoFamily,
                                fontSize = 15.sp,
                                color = Color.Black
                            )
                        ) {
                            append(" v4R2")
                        }
                    },
                    minLines = 2,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Text(
                    text = MainStrings.tonConnectHint,
                    fontFamily = robotoFamily,
                    fontSize = 15.sp,
                    color = TonGray,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    minLines = 2,
                    modifier = Modifier
                        .padding(top = 36.dp, start = 40.dp, end = 40.dp)
                )
                ConnectButton()
            }
        }
    }
}