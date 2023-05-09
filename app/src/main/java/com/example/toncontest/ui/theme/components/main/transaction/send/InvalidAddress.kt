package com.example.toncontest.ui.theme.components.main.transaction.send

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.robotoFamily

@Preview
@Composable
fun InvalidAddressPopUp () {
    val TonDarkGray = Color(0xFF2F373F)
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .background(
                shape = RoundedCornerShape(6.dp), brush = Brush.horizontalGradient(
                    mutableListOf(TonDarkGray, TonDarkGray)
                ), alpha = .92f
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.errorsign),
                contentDescription = "Error sign",
                tint = Color.White
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = MainStrings.invalidAddressTitle,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = MainStrings.invalidAddressSubtitle,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = robotoFamily
                )
            }
        }
    }
}