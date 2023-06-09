package com.example.toncontest.ui.theme.screens.main.receive

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.data.ton.account.account
import com.example.toncontest.ui.theme.TonGray
import com.example.toncontest.ui.theme.components.main.CardTitle
import com.example.toncontest.ui.theme.components.main.ShareButton
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun ReceiveCard(onDrag: (Boolean) -> Unit, context: Context) {
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
                        if (velocity > 0)
                            onDrag(false)
                    },
                    orientation = Orientation.Vertical
                ),
            verticalArrangement = Arrangement.Bottom
        ) {
            CardTitle(text = MainStrings.receiveHeaderText)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = MainStrings.receiveMainText,
                    fontSize = 15.sp,
                    fontFamily = robotoFamily,
                    color = TonGray,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(275.dp)
                )
            }
            ShareQR(context = context)
            Text(
                text = address,
                minLines = 2,
                maxLines = 2,
                textAlign = TextAlign.Center,
                fontFamily = robotoFamily,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 28.dp)
                    .fillMaxWidth()
            )
            ShareButton(address = accAddress)
        }
    }
}