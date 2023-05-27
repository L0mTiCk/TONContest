package com.example.toncontest.ui.theme.screens.main

import android.content.Context
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.toncontest.R
import com.example.toncontest.data.Data
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.Light_Gray
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.start.PassLoader

@Composable
fun LogInScreen(navController: NavController, context: Context) {
    var numOfDigits by remember { mutableStateOf(4) }
    var expanded by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(mutableListOf<Int>()) }
    var currentIndex by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var isNavigated by remember { mutableStateOf(false) }


    @Composable
    fun PasscodeKeyboard() {

        @Composable
        fun NumButton(index: Int) {
            var backColor = if (index != -2) Light_Gray else Color.Transparent
            Button(
                onClick = {
                    if (currentIndex < numOfDigits) {
                        password.add(index)
                        currentIndex++
                        Log.d("passcode", password.toString())
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
                Text(
                    text = if (index != -1) index.toString() else "",
                    fontFamily = robotoFamily,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    modifier = Modifier.width(45.dp)
                )
                Text(
                    text =
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
        fun BackspaceButton() {
            Button(
                onClick = {
                    if (currentIndex != 0) {
                        password.removeAt(currentIndex - 1)
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
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.backspace),
                        contentDescription = "Test",
                    )
                }
            }
        }

        Box(
            modifier = Modifier
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
            offset = DpOffset(0.dp, (-175).dp)
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

    val sharedPref = context.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)
    val confirmPassword = sharedPref.getString("PASSWORD", "")

    if (currentIndex == numOfDigits) {
        if (password.toString() == confirmPassword && !isNavigated) {
            if (navController.previousBackStackEntry == null) {
                navController.navigate("main") {
                    popUpTo("login") {
                        inclusive = true
                    }
                }
                isNavigated = true
            } else {
                navController.popBackStack()
            }
            password.clear()
            currentIndex = 0
        } else {
            Log.d("login", navController.previousBackStackEntry.toString())
            password.clear()
            currentIndex = 0
            showDialog = true
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            backgroundColor = Color.White,
            title = { Text(text = Data.passcodeAlertTitle) },
            text = { Text(text = Data.passcodeAlertText) },
            confirmButton = {
                Text(text = Data.okButtonText,
                    color = Light_Blue,
                    fontFamily = robotoFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            showDialog = false
                        }
                        .padding(20.dp)
                )
            }
        )
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


    //UI
    Scaffold(
        topBar = { Row(modifier = Modifier.height(56.dp), content = { }) },
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(numOfDigits) { index ->
                        val circleColor = animateColorAsState(
                            targetValue =
                            if (index < currentIndex) Color.Black else Color.LightGray
                        )
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(4.dp)
                                .background(color = circleColor.value, shape = CircleShape)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
            Row(modifier = Modifier.width(200.dp)) {
                if (expanded) {
                    DropDownPasscode()
                }
                PasscodeButton(text = Data.passcodeButtonText)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.Bottom, modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 15.dp)
            ) {
                PasscodeKeyboard()
            }
        }
    }
}