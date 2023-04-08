package com.example.toncontest.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.toncontest.data.Data
import com.example.toncontest.data.congrats.checkForDelay
import com.example.toncontest.data.congrats.setCurrentTime
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun ButtonWithAlertDialog(textColor: Color = Color.White, backColor: Color = Light_Blue, text: String, navController: NavController, route: String) {
    var showDialog = remember { mutableStateOf(false) }
    val dialogButtonCount = remember { mutableStateOf(1) }

    Button(colors = ButtonDefaults.buttonColors(backgroundColor = backColor),
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp),
        onClick = {
            setCurrentTime()
            if(checkForDelay())
                showDialog.value = true
            else
                navController.navigate(route)
        }
    ) {
        Text(text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier
                .width(200.dp)
                .padding(top = 14.dp, bottom = 14.dp)
        )    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            backgroundColor = Color.White,
            contentColor = Color.Black,
            title = { Text(text = Data.alertTitle) },
            text = { Text(Data.alertText) },
            confirmButton = {
                if (dialogButtonCount.value == 1) {
                    Text(text = Data.okButtonText,
                        color = Light_Blue,
                        fontFamily = robotoFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                showDialog.value = false
                                dialogButtonCount.value++
                            }
                            .padding(20.dp)
                    )
                } else {
                    Text(text = Data.skipButtonText,
                        color = Light_Blue,
                        fontFamily = robotoFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                showDialog.value = false
                                navController.navigate(route = route)
                            }
                            .padding(20.dp)
                    )
                    Text(text = Data.okButtonText,
                        color = Light_Blue,
                        fontFamily = robotoFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                showDialog.value = false
                            }
                            .padding(20.dp)
                    )
                }
            }
        )
    }
}

@Composable
fun NavBack(navController: NavController){
    Column(modifier = Modifier
        .fillMaxWidth()
    ){
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "BackArrow",
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .padding(top = 6.dp)
                    .clickable { navController.popBackStack()},
            )
        }
    }

}

@Composable
fun Loader(res: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))
        LottieAnimation(composition = composition, iterations = LottieConstants.IterateForever, modifier = Modifier.weight(1f))
    }
}


@Composable
fun BackgroundButton(text: String, backColor: Color, textColor: Color = Color.White, navController: NavController, route: String){
    Button(onClick = { navController.navigate(route) },
        colors = ButtonDefaults.buttonColors(backgroundColor = backColor),
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.elevation(0.dp, 0.dp)
    ) {
        Text(text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            modifier = Modifier
                .width(200.dp)
                .padding(top = 14.dp, bottom = 14.dp)
        )
    }
}

@Composable
fun DefaultText(text: String = "Text", textColor: Color = Color.Black, fontFamily: FontFamily, fontWeight: FontWeight,
                fontSize: TextUnit, width: Dp, paddingStart: Dp = 0.dp, textAlign: TextAlign = TextAlign.Start,
                paddingTop: Dp = 0.dp, paddingBottom: Dp = 0.dp, paddingEnd: Dp = 0.dp
) {
    Text(text = text,
        color = textColor,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontSize = fontSize,
        fontWeight = fontWeight,
        modifier = Modifier
            .width(width)
            .padding(
                start = paddingStart,
                top = paddingTop,
                bottom = paddingBottom,
                end = paddingEnd
            )
        )
}