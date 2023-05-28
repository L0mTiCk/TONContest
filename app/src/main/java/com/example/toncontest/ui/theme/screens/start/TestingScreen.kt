package com.example.toncontest.ui.theme.screens.start

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.R
import com.example.toncontest.data.Data
import com.example.toncontest.data.testing.checkCorrectness
import com.example.toncontest.data.testing.checkRandom
import com.example.toncontest.data.testing.mnemonicRandom
import com.example.toncontest.data.testing.textFieldsInput
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.*
import org.ton.mnemonic.Mnemonic

@Composable
fun TestingScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false)}
    checkRandom()
    @Composable
    fun TestingAlert(navController: NavController, show: Boolean) {
        var showDialogLocal = remember { mutableStateOf(show) }
        if (showDialogLocal.value) {
            AlertDialog(
                onDismissRequest = { showDialogLocal.value = false; showDialog = false },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                title = { Text(text = Data.testAlertTitle) },
                text = { Text(Data.testAlertText) },
                confirmButton = {
                    Text(text = Data.testWordsButtonText,
                        color = Light_Blue,
                        fontFamily = robotoFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                showDialogLocal.value = false
                                showDialog = false
                                navController.popBackStack()
                            }
                            .padding(20.dp)
                    )
                    Text(text = Data.testAgainButtonText,
                        color = Light_Blue,
                        fontFamily = robotoFamily,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                showDialogLocal.value = false
                                showDialog = false
                            }
                            .padding(20.dp)
                    )
                }
            )
        }
    }

    @Composable
    fun TestingBackButton(text: String, backColor: Color, textColor: Color = Color.White, navController: NavController, route: String){
        Button(onClick = {
            if (checkCorrectness())
                navController.navigate(route)
            else {
                showDialog = true
            }
        },
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
        if(showDialog) {
            TestingAlert(navController = navController, showDialog)
        }
    }

    Scaffold(
        topBar = { NavBack(navController = navController
        ) },
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Loader(res = R.raw.testtime)
            DefaultText(
                text = Data.testTimeHeaderText,
                fontFamily = robotoFamily,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                width = 240.dp,
                paddingTop = 12.dp
            )
            DefaultText(
                text = Data.testTimeMainText,
                fontFamily = robotoFamily,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                width = 280.dp,
                paddingTop = 12.dp
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 28.dp)
                    //.height(170.dp)
            ) {
                items(mnemonicRandom) {  item ->
                    TestingTextInput(
                        number = item,
                        Modifier
                            .padding(bottom = 8.dp)
                            .width(200.dp)
                            .height(55.dp)
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                TestingBackButton(
                    text = "Proceed",
                    backColor = Light_Blue,
                    route = "success",
                    navController = navController
                )
                Spacer(modifier = Modifier.height(108.dp))

            }
        }
    }
}

@Composable
fun TestingTextInput(number: Int, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var hintList by remember { mutableStateOf(mutableListOf("")) }

    LaunchedEffect(key1 = text) {
        if (text.isNotEmpty()) {
            hintList = Mnemonic.mnemonicWords()
                .filter { it.startsWith(text, ignoreCase = true) } as MutableList<String>
            Log.d("importHint", hintList.toString())
        }
    }

    isError = !text.isEmpty() && !Mnemonic.mnemonicWords().contains(text)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isFocused && isError && hintList.isNotEmpty(),
        ) {
            LazyRow(
                modifier = Modifier
                    .scrollable(rememberScrollState(), orientation = Orientation.Horizontal)
                    .shadow(3.dp, shape = RoundedCornerShape(10.dp))
                    .background(Color.White, RoundedCornerShape(10.dp))
                    .widthIn(max = 200.dp)
                    .height(45.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                items(hintList) {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                text = it
                                textFieldsInput[mnemonicRandom.indexOf(number)] = it
                            }
                            .indication(MutableInteractionSource(), null),
                    )
                }
            }
        }
        TextField(
            value = text,
            singleLine = true,
            onValueChange = {
                text = it.replace(" ", "")
                textFieldsInput[mnemonicRandom.indexOf(number)] = it
            },
            leadingIcon = {
                Text(
                    text = "$number:",
                    color = Color.Gray,
                    fontSize = 15.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.width(24.dp)
                )
            },
            modifier = Modifier
                .onFocusChanged { isFocused = it.isFocused }
                .height(55.dp)
                .width(200.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Light_Blue,
                unfocusedIndicatorColor = Color.Gray,
                disabledIndicatorColor = Color.Gray
            ),
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )
    }
}

