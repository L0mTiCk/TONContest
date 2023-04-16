package com.example.toncontest.ui.theme.screens.start

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.R
import com.example.toncontest.data.Data
import com.example.toncontest.data.importCheck
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.DefaultText
import com.example.toncontest.ui.theme.screens.Loader
import com.example.toncontest.ui.theme.screens.NavBack

@Composable
fun ImportScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    @Composable
    fun ImportAlert(show: Boolean) {
        var showDialogLocal = remember { mutableStateOf(show) }
        if (showDialogLocal.value) {
            AlertDialog(
                onDismissRequest = { showDialogLocal.value = false; showDialog = false },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                title = { Text(text = Data.importAlertTitle) },
                text = { Text(Data.importAlertText) },
                confirmButton = {
                    Text(text = Data.importAlertButtonText,
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
    fun ImportBackButton(
        text: String,
        backColor: Color,
        textColor: Color = Color.White,
        navController: NavController,
        route: String
    ) {
        Button(
            onClick = {
                if (importCheck())
                    navController.navigate(route)
                else {
                    showDialog = true
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = backColor),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp)
        ) {
            Text(
                text = text,
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
        if (showDialog) {
            ImportAlert(showDialog)
        }
    }
    Scaffold(
        topBar = {
            NavBack(
                navController = navController
            )
        },
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(rememberScrollState(), enabled = true)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Loader(res = R.raw.recoveryphrase)
            DefaultText(
                text = Data.importHeaderText,
                fontFamily = robotoFamily,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                width = 240.dp,
                paddingTop = 12.dp
            )
            DefaultText(
                text = Data.importMainText,
                fontFamily = robotoFamily,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                width = 280.dp,
                paddingTop = 12.dp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = Data.importNoMnemonicText,
                fontFamily = robotoFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Light_Blue,
                modifier = Modifier
                    .clickable { navController.navigate("noMnemonic") }
            )
            Column(
                modifier = Modifier
                    .height(1484.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 20.dp,),
                    userScrollEnabled = false,
                    ) {
                    items(24) { item ->
                        ImportTextInput(
                            number = item + 1,
                            Modifier
                                .padding(bottom = 8.dp)
                                .width(200.dp)
                                .height(55.dp)
                        )
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 28.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                ImportBackButton(
                    text = Data.importButtonText,
                    backColor = Light_Blue,
                    route = "success",
                    navController = navController
                )
                Spacer(modifier = Modifier.height(56.dp))

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ImportTextInput(number: Int, modifier: Modifier = Modifier){
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        TextField(
            value = text,
            singleLine = true,
            onValueChange = {
                text = it
                Data.importMnemonic[number - 1] = it
            },
            leadingIcon = { Text(text = "$number:", color = Color.Gray, fontSize = 15.sp, textAlign = TextAlign.End, modifier = Modifier.width(24.dp) ) },
            modifier = Modifier
                .onFocusChanged { isFocused = it.isFocused }
                .height(55.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Light_Blue,
                unfocusedIndicatorColor = Color.Gray,
                disabledIndicatorColor = Color.Gray
            )
        )
    }
}

