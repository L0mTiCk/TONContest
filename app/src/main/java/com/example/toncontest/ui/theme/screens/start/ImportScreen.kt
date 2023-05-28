package com.example.toncontest.ui.theme.screens.start

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.toncontest.data.start.importCheck
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.DefaultText
import com.example.toncontest.ui.theme.screens.Loader
import com.example.toncontest.ui.theme.screens.NavBack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.ton.mnemonic.Mnemonic

var isImport = false

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImportScreen(navController: NavController, context: Context) {
    var showDialog by remember { mutableStateOf(false) }
    var isChecking by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }
    var scrollState = rememberScrollState()

    val alpha: Float by animateFloatAsState(
        targetValue = if (scrollState.value < 450) 1f else 0f,
        animationSpec = tween(
            durationMillis = 100,
            easing = LinearEasing
        )
    )

    LaunchedEffect(key1 = isChecking) {
        if (isChecking) {
            CoroutineScope(Dispatchers.IO).launch {
                if (importCheck()) {
                    isValid = true
                } else {
                    showDialog = true
                }
                isChecking = false
            }
        }
    }

    LaunchedEffect(key1 = isValid) {
        if (isValid) {
            navController.navigate("success")
            isImport = true
            val sharedPref = context.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)
            sharedPref.edit().putString("MNEMONIC", Data.importMnemonic.joinToString("|")).apply()
            isValid = false
        }
    }

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
                isChecking = true
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = backColor),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp),
            modifier = Modifier
                .width(200.dp)
        ) {
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = text,
                color = textColor,
                textAlign = TextAlign.Center,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 14.dp)
                    .weight(1f)
            )
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 2.dp,
                modifier = Modifier
                    .size(24.dp)
                    .alpha(animateFloatAsState(targetValue = if (isChecking) 1f else 0f).value)
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
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                Text(text = Data.importHeaderText,
                    fontFamily = robotoFamily,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.alpha(1 - alpha)
                )
            }
        },
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .verticalScroll(scrollState, true)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Loader(res = R.raw.recoveryphrase)
            Text(
                text = Data.importHeaderText,
                fontFamily = robotoFamily,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.alpha(alpha)
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
                    .height(1532.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    userScrollEnabled = false,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                    items(24) { item ->
                        ImportTextInput(
                            number = item + 1,
                            Modifier
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

@Composable
fun ImportTextInput(number: Int, modifier: Modifier = Modifier) {
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
                                    Data.importMnemonic[number - 1] = it
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
                Data.importMnemonic[number - 1] = it.replace(" ", "")
                Log.d("transactions", "textfield №$number " + Data.importMnemonic[number - 1])
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