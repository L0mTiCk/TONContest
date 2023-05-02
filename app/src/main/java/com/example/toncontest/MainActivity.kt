package com.example.toncontest

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.toncontest.data.Data
import com.example.toncontest.data.start.Biometric
import com.example.toncontest.ui.theme.TONContestTheme
import com.example.toncontest.ui.theme.screens.main.LogInScreen
import com.example.toncontest.ui.theme.screens.main.MainScreen
import com.example.toncontest.ui.theme.screens.main.settings.SettingsScreen
import com.example.toncontest.ui.theme.screens.start.RecoveryScreen
import com.example.toncontest.ui.theme.screens.start.DoneScreen
import com.example.toncontest.ui.theme.screens.start.ImportScreen
import com.example.toncontest.ui.theme.screens.start.NoMnemonicScreen
import com.example.toncontest.ui.theme.screens.start.PasscodeScreen
import com.example.toncontest.ui.theme.screens.start.StartScreen
import com.example.toncontest.ui.theme.screens.start.SuccessImportScreen
import com.example.toncontest.ui.theme.screens.start.SuccessScreen
import com.example.toncontest.ui.theme.screens.start.TestingScreen

class MainActivity : FragmentActivity() {
    private var navigateFunction: ((Boolean) -> Unit)? = null
    private var biometricFunction: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = this.getSharedPreferences("MY_APP_PREFERENCES", Context.MODE_PRIVATE)
        val isWalletCreated = sharedPref.getBoolean("CREATED", false)
        val startDestination = if (isWalletCreated) "login" else "start"
        val isBiometric = sharedPref.getBoolean("BIOMETRIC", false)
        setContent {
            val navController = rememberNavController()

            val context = LocalContext.current
            setBiometricFunction { it ->
                if (it) {
                    Biometric.authenticate(
                        this@MainActivity,
                        title = "Biometric Authentication",
                        subtitle = "Authenticate to proceed",
                        description = "",
                        negativeText = "Cancel",
                        onSuccess = {
                            navController.navigate("main") {
                                popUpTo("login") {
                                    inclusive = true
                                }
                            }
                        },
                        onError = { _, _ ->

                        },
                        onFailed = {

                        }
                    )
                }
            }
            biometricFunction?.invoke(isBiometric)

            TONContestTheme(darkTheme = false) {
                setNavigateFunction { it ->
                    if (it) {
                        navController.navigate("login")
                    }
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Box(Modifier.fillMaxSize()) {
                        NavHost(navController = navController, startDestination = startDestination) {
                            composable(route = "start") { StartScreen(navController = navController) }
                            composable(route = "congrats") { CongratsScreen(navController = navController) }
                            composable(route = "recovery") { RecoveryScreen(navController = navController, Data.isFirstLaunch) }
                            composable(route = "testing") { TestingScreen(navController = navController) }
                            composable(route = "success") { SuccessScreen(navController = navController, context = context) }
                            composable(route = "passcode") { PasscodeScreen(navController = navController, this@MainActivity) }
                            composable(route = "import") { ImportScreen(navController = navController) }
                            composable(route = "successImport") { SuccessImportScreen(navController = navController) }
                            composable(route = "noMnemonic") { NoMnemonicScreen(navController = navController) }
                            composable(route = "done") { DoneScreen(navController = navController) }

                            //TODO: redo to another activity launch
                            composable(route = "main") { MainScreen(navController = navController) }
                            composable(route = "settings") { SettingsScreen(navController = navController, context = this@MainActivity) }
                            composable(route = "login") { LogInScreen( navController = navController, context = this@MainActivity) }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = this.getSharedPreferences("MY_APP_PREFERENCES", Context.MODE_PRIVATE)
        val isBiometric = sharedPref.getBoolean("BIOMETRIC", false)
        navigateFunction?.invoke(true)
        if (isBiometric)
            biometricFunction?.invoke(true)
    }
    fun setNavigateFunction(navigate: (Boolean) -> Unit) {
        navigateFunction = navigate
    }
    fun setBiometricFunction(navigate: (Boolean) -> Unit) {
        biometricFunction = navigate
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TONContestTheme {

    }
}
