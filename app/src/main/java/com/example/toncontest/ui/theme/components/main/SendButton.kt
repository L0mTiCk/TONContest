package com.example.toncontest.ui.theme.components.main

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.R
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun SendButton(){
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Light_Blue),
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        modifier = Modifier
            .width(158.dp)
            .height(48.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.call_made),
            contentDescription = "Receive arrow",
            tint = Color.White,
            modifier = Modifier
                .padding(end = 6.dp)
                .width(18.dp)
                .height(18.dp)
        )
        Text(text = MainStrings.sendButton,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier
                .height(20.dp)
        )
    }
}