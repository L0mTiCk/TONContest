package com.example.toncontest.ui.theme.screens.start

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.example.toncontest.R
import com.example.toncontest.data.Data
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.Light_Gray
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.NavBack

@Composable
fun PasscodeScreen(navController: NavController) {
    var numOfDigits by remember { mutableStateOf(4) }
    var expanded by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(mutableListOf<Int>()) }
    var currentIndex by remember { mutableStateOf(0) }
    var isConfirmation by remember { mutableStateOf(false) }
    var isNavigate by remember { mutableStateOf(false) }

    @Composable
    fun PasscodeKeyboard(){

        @Composable
        fun NumButton(index: Int){
            var backColor = if (index != -2) Light_Gray else Color.Transparent
            Button(
                onClick = {
                    //TODO: make this shit working
                    if(currentIndex < numOfDigits) {
                        password.add(index)
                        currentIndex++
                        if(currentIndex == numOfDigits && isConfirmation)
                            isNavigate = true
                        if(currentIndex == numOfDigits)
                            isConfirmation = true

                        Log.d("passcode", password.toString())
                    }

                    if (currentIndex == numOfDigits) {
                        if (!isConfirmation) {
                            isConfirmation = true
                            Data.passcodeForConfirm = password
                            password.clear()
                            currentIndex = 0
                        } else if (isConfirmation && password == Data.passcodeForConfirm) {
                            navController.navigate("done")
                        } else {
                            password.clear()
                            currentIndex = 0
                        }
                    }
                          },
                modifier = Modifier
                    .width(109.dp)
                    .height(47.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = backColor
                ),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp)
            ) {
                Text(text = if (index != -1) index.toString() else "",
                    fontFamily = robotoFamily,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.width(45.dp)
                )
                Text(text =
                if (index != -1 && index != 0)
                    Data.lettersForButtons[index]
                else if (index != -1 && index == 0)
                    Data.lettersForButtons[11]
                else "",
                    fontFamily = robotoFamily,
                    fontSize = 14.sp,
                    color = Color.LightGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        @Composable
        fun BackspaceButton(){
            Button(
                onClick = {
                    if(currentIndex != 0) {
                        password.removeAt(currentIndex-1)
                        currentIndex--
                    }
                    Log.d("passcode", password.toString())
                },
                modifier = Modifier
                    .width(109.dp)
                    .height(47.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Light_Gray
                ),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp)
            ) {
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Image(painter = painterResource(id = R.drawable.backspace), contentDescription = "Test")
                }
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                itemsIndexed(Data.numsForButtons) { index, item ->
                    if (index != 9 && index != 11)
                        NumButton(index = item)
                    else if (index == 11)
                        BackspaceButton()
                }
            }
        }
    }

    @Composable
    fun DropDownPasscode() {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false; },
            modifier = Modifier.width(200.dp),
            offset = DpOffset(0.dp, -175.dp)
        ) {
            DropdownMenuItem(onClick = {
                numOfDigits = 4
                expanded = false
            }
            ) {
                Text(text = "4-digit code")
            }
            DropdownMenuItem(onClick = {
                numOfDigits = 6
                expanded = false
            }
            ) {
                Text(text = "6-digit code")
            }
        }
    }


    @Composable
    fun PasscodeButton(text: String) {
        Button(
            onClick = {
                expanded = true
                password.clear()
                currentIndex = 0
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp),
            modifier = Modifier.padding(top = 31.dp),
            enabled = !isConfirmation,
        ) {
            Text(
                text = text,
                color = Light_Blue,
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

    /*if(isConfirmation && passSaved){
        Data.passcodeForConfirm = password
        password.clear()
        currentIndex = 0
        passSaved = false
    }

     */


    /*if(isNavigate) {
        if(password.equals(Data.passcodeForConfirm)) {
            navController.navigate("start")
            isNavigate = false
        }
    }

     */

    //UI
    Scaffold(
        topBar = { NavBack(navController = navController) },
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Spacer(modifier = Modifier.height(100.dp))
            PassLoader(R.raw.password)
            Text(
                text = if(!isConfirmation) Data.setPasscodeHeaderText else Data.confirmPasscodeHeaderText,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = robotoFamily,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
            )
            Text(
                text = Data.passcodeMainText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = robotoFamily,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(280.dp)
            )
            Box(modifier = Modifier.padding(top = 31.dp)) {
//                TextField(
//                    value = password,
//                    onValueChange = { password = it },
//                    colors = TextFieldDefaults.textFieldColors(
//                        textColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent,
//                        backgroundColor = Color.Transparent,
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        unfocusedLabelColor = Color.Transparent
//                    ),
//                    modifier = Modifier
//                        .alpha(0f)
//                        .background(Color.Transparent),
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.NumberPassword,
//                        imeAction = ImeAction.Done
//                    ),
//                    visualTransformation = VisualTransformation.None
//                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(numOfDigits) { index ->
                        val circleColor =
                            if (index < currentIndex) Color.Black else Color.LightGray
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(4.dp)
                                .background(color = circleColor, shape = CircleShape)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
            if(!isConfirmation) {
                Row(modifier = Modifier.width(200.dp)) {
                    if (expanded) {
                        DropDownPasscode()
                    }
                    PasscodeButton(text = Data.passcodeButtonText)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.Bottom, modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 15.dp)){
                PasscodeKeyboard()
            }
        }
    }
}

@Composable
fun PassLoader(res: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(res))
        LottieAnimation(composition = composition, clipSpec = LottieClipSpec.Progress(0f, .5f),  modifier = Modifier.weight(1f))
    }
}