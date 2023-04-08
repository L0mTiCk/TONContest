package com.example.toncontest.ui.theme.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.R
import com.example.toncontest.data.Data
import com.example.toncontest.data.congrats.setEntryTime
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily

@Composable
fun RecoveryScreen(navController: NavController) {
    //set entry time
    setEntryTime()

    //UI
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Scaffold(
            topBar = { NavBack(navController = navController ) },
            backgroundColor = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f, fill = true)
                    .padding(it)
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Loader(res = R.raw.recoveryphrase)
                DefaultText(text = Data.recoveryHeaderText, fontFamily = robotoFamily, textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold, fontSize = 24.sp , width = 240.dp)
//                Text(
//                    text = Data.recoveryHeaderText,
//                    fontFamily = robotoFamily,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 24.sp,
//                    modifier = Modifier
//                        .padding(top = 12.dp)
//                )
                DefaultText(text = Data.recoveryMainText, fontFamily = robotoFamily, textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium, fontSize = 15.sp , width = 280.dp)
                MnemonicPhrases(
                    mnemonics = Data.testMnemon
                )
                Spacer(modifier = Modifier.height(40.dp))
                ButtonWithAlertDialog(text = "Done", backColor = Light_Blue,
                    navController = navController, route = "testing")
                Spacer(modifier = Modifier.height(56.dp))
            }
        }
    }
}

@Composable
fun MnemonicPhrases(mnemonics: List<String>) {
    Box(
        modifier = Modifier
            .width(280.dp)
            .padding(top = 40.dp)

    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(12),
            modifier = Modifier
                .height(330.dp),
            userScrollEnabled = false,
            horizontalArrangement = Arrangement.spacedBy(60.dp, Alignment.End),
        ) {
            itemsIndexed(mnemonics) { index, item ->
                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.LightGray)) {
                            append("${index + 1}.  ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        ) {
                            append("$item")
                        }
                    }
                )

            }
        }   
    }
}





