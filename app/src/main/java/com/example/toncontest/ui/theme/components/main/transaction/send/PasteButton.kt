package com.example.toncontest.ui.theme.components.main.transaction.send

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun PasteButton(paste: (String) -> Unit) {
    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = Modifier
            .clickable {
                var bufer = clipboardManager.getText().toString()
                if (bufer != "null")
                    paste(bufer)
            }
    ) {
        Row() {
            Icon(
                painter = painterResource(
                    id = R.drawable.paste
                ),
                contentDescription = "Paste",
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp),
                tint = Light_Blue
            )
            Text(
                text = MainStrings.pasteButtonText,
                color = Light_Blue,
                fontSize = 14.sp,
                fontFamily = robotoFamily,
                modifier = Modifier
                    .padding(start = 4.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}