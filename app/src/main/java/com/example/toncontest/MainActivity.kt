package com.example.toncontest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.toncontest.ui.theme.TONContestTheme
import com.example.toncontest.ui.theme.screens.RecoveryScreen
import com.example.toncontest.ui.theme.screens.start.SuccessScreen
import com.example.toncontest.ui.theme.screens.start.TestingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TONContestTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Box(Modifier.fillMaxSize()) {
                        NavHost(navController = navController, startDestination = "success") {
                            composable(route = "start") { StartScreen(navController = navController) }
                            composable(route = "congrats") { CongratsScreen(navController = navController) }
                            composable(route = "recovery") { RecoveryScreen(navController = navController) }
                            composable(route = "testing") { TestingScreen(navController = navController) }
                            composable(route = "success") { SuccessScreen(navController = navController) }
                        }
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TONContestTheme {
    }
}
