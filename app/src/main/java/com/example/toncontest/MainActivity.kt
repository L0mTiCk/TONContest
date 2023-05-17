package com.example.toncontest

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.toncontest.data.Data
import com.example.toncontest.data.start.Biometric
import com.example.toncontest.ui.theme.TONContestTheme
import com.example.toncontest.ui.theme.screens.main.LogInScreen
import com.example.toncontest.ui.theme.screens.main.MainScreen
import com.example.toncontest.ui.theme.screens.main.send.SendAmountScreen
import com.example.toncontest.ui.theme.screens.main.send.SendConfirmScreen
import com.example.toncontest.ui.theme.screens.main.send.SendFinalScreen
import com.example.toncontest.ui.theme.screens.main.send.SendStartScreen
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
        sharedPref.edit().clear().apply()
        val isWalletCreated = sharedPref.getBoolean("CREATED", false)
        val startDestination = if (isWalletCreated) "main" else "main"
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
                            if (navController.previousBackStackEntry == null)
                                navController.navigate("main") {
                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            else
                                navController.popBackStack()
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
                            composable(route = "recovery") { RecoveryScreen(navController = navController, Data.isFirstLaunch, context = this@MainActivity) }
                            composable(route = "testing") { TestingScreen(navController = navController) }
                            composable(route = "success") { SuccessScreen(navController = navController, context = context) }
                            composable(route = "passcode") { PasscodeScreen(navController = navController, this@MainActivity) }
                            composable(route = "import") { ImportScreen(navController = navController) }
                            composable(route = "successImport") { SuccessImportScreen(navController = navController) }
                            composable(route = "noMnemonic") { NoMnemonicScreen(navController = navController) }
                            composable(route = "done") { DoneScreen(navController = navController) }

                            //TODO: redo to another activity launch
                            composable(route = "main") { MainScreen(navController = navController, context = this@MainActivity) }
                            composable(route = "settings") { SettingsScreen(navController = navController, context = this@MainActivity) }
                            composable(route = "login") { LogInScreen( navController = navController, context = this@MainActivity) }
                            composable(route = "sendStart") { SendStartScreen(navController = navController) }
                            composable(route = "sendAmount") { SendAmountScreen(navController = navController) }
                            composable(route = "sendConfirm") { SendConfirmScreen(navController = navController) }
                            composable(route = "sendPending") { SendFinalScreen(navController = navController) }
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
        val isCreated = sharedPref.getBoolean("CREATED", false)
        navigateFunction?.invoke(isCreated)
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