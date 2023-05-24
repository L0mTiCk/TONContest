package com.example.toncontest.ui.theme.components.main.transaction.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun ViewInExplorerField(id: String, context: Context) {
    Column(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth()
            .clickable {
                var url = "https://dton.io/tx/$id"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(context, intent, Bundle.EMPTY)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = MainStrings.transactionExplorerTitle,
                fontFamily = robotoFamily,
                fontSize = 15.sp,
                color = Light_Blue,
                modifier = Modifier
                    .padding(vertical = 14.dp)
            )
        }
    }
}
