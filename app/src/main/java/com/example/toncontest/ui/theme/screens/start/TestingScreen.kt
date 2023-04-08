package com.example.toncontest.ui.theme.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.toncontest.data.testing.checkRandom
import com.example.toncontest.data.testing.leftScreen
import com.example.toncontest.data.testing.mnemonicRandom
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.Shapes
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.*

@Composable
fun TestingScreen(navController: NavController) {
    checkRandom()

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
                    .height(170.dp)
            ) {
                items(mnemonicRandom) {  item ->
                    TestingTextInput(
                        number = item,
                        Modifier
                            .padding(bottom = 12.dp)
                            .width(200.dp)
                            .height(44.dp)
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
                BackgroundButton(
                    text = "Proceed",
                    backColor = Light_Blue,
                    route = "recovery",

                    navController = navController
                )
                Spacer(modifier = Modifier.height(108.dp))

            }
        }
    }
}

@Composable
fun TestingTextInput(number: Int, modifier: Modifier = Modifier){
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = { text = it },
            leadingIcon = { Text(text = "$number:", color = Color.Gray,textAlign = TextAlign.End, modifier = Modifier.width(29.dp) )},
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .onFocusChanged { isFocused = it.isFocused }
                .verticalScroll(state = rememberScrollState(), enabled = false),
            textStyle = MaterialTheme.typography.body1,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Light_Blue,
                unfocusedIndicatorColor = Color.Gray,
                disabledIndicatorColor = Color.Gray
            ),
        )
    }
}
