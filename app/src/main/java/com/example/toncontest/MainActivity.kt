package com.example.toncontest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.toncontest.data.Data
import com.example.toncontest.data.main.send.sendInfo
import com.example.toncontest.data.start.Biometric
import com.example.toncontest.data.ton.client.liteClient
import com.example.toncontest.ui.theme.TONContestTheme
import com.example.toncontest.ui.theme.screens.main.LogInScreen
import com.example.toncontest.ui.theme.screens.main.MainScreen
import com.example.toncontest.ui.theme.screens.main.qrcode.NoCameraScreen
import com.example.toncontest.ui.theme.screens.main.send.SendAmountScreen
import com.example.toncontest.ui.theme.screens.main.send.SendConfirmScreen
import com.example.toncontest.ui.theme.screens.main.send.SendFinalScreen
import com.example.toncontest.ui.theme.screens.main.send.SendStartScreen
import com.example.toncontest.ui.theme.screens.main.settings.SettingsScreen
import com.example.toncontest.ui.theme.screens.start.DoneScreen
import com.example.toncontest.ui.theme.screens.start.ImportScreen
import com.example.toncontest.ui.theme.screens.start.NoMnemonicScreen
import com.example.toncontest.ui.theme.screens.start.PasscodeScreen
import com.example.toncontest.ui.theme.screens.start.RecoveryScreen
import com.example.toncontest.ui.theme.screens.start.StartScreen
import com.example.toncontest.ui.theme.screens.start.SuccessImportScreen
import com.example.toncontest.ui.theme.screens.start.SuccessScreen
import com.example.toncontest.ui.theme.screens.start.TestingScreen

class MainActivity : FragmentActivity() {
    private var navigateFunction: ((Boolean) -> Unit)? = null
    private var biometricFunction: ((Boolean) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = this.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)
        //TODO: remove this line
        //sharedPref.edit().clear().apply()
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
                            composable(route = "import") { ImportScreen(navController = navController, this@MainActivity) }
                            composable(route = "successImport") { SuccessImportScreen(navController = navController) }
                            composable(route = "noMnemonic") { NoMnemonicScreen(navController = navController) }
                            composable(route = "done") { DoneScreen(navController = navController) }

                            composable(route = "main") { MainScreen(navController = navController, context = this@MainActivity) }
                            composable(route = "settings") { SettingsScreen(navController = navController, context = this@MainActivity) }
                            composable(route = "login") { LogInScreen( navController = navController, context = this@MainActivity) }
                            composable(route = "sendStart") { SendStartScreen(navController = navController) }
                            composable(route = "sendStart/{param}") {backStackEntry ->
                                val param = backStackEntry.arguments?.getString("param")
                                if (param != null) {
                                    SendStartScreen(navController = navController, param)
                                } else {
                                    SendStartScreen(navController = navController)
                                }
                            }
                            composable(route = "sendAmount") { SendAmountScreen(navController = navController) }
                            composable(route = "sendConfirm") { SendConfirmScreen(navController = navController) }
                            composable(route = "sendFinal") { SendFinalScreen(navController = navController) }
                            composable(route = "noCamera") { NoCameraScreen(navController = navController, context = this@MainActivity) }
                            //deeplinks

                            //idk why, but if there are 2, even on ton://transfer/..... click share screen opens
//                            composable(
//                                route = "mainShare",
//                                deepLinks = listOf(
//                                    navDeepLink {
//                                        uriPattern = "ton://share"
//                                        action = Intent.ACTION_VIEW
//                                    }
//                                )
//                            ) {
//                                Log.d("transactions", "Deeplink main navigation")
//                                MainScreen(navController = navController, context = this@MainActivity, isReceive = true)
//                            }
                            composable(
                                route = "sendConfirmDeepl",
                                deepLinks = listOf(
                                    navDeepLink {
                                        uriPattern = "ton://transfer/{wallet_address}?amount={amount}&text={comment}"
                                        action = Intent.ACTION_VIEW
                                    }
                                ),
                                arguments = listOf(
                                    navArgument("wallet_address") {
                                        type = NavType.StringType
                                        defaultValue = "WasdWasdWasdWasdWasdWasdWasdWasdWasdWasdWasdWasd"
                                    },
                                    navArgument("amount") {
                                        type = NavType.StringType
                                        defaultValue = "0.04"
                                    },
                                    navArgument("comment") {
                                        type = NavType.StringType
                                        defaultValue = "default deeplink"
                                    }
                                )
                            ) {backStackEntry ->
                                sendInfo.recipient = (backStackEntry.arguments?.getString("wallet_address") ?: "" )
                                sendInfo.amount = (backStackEntry.arguments?.getString("amount")?.toDouble() ?: 0.01 )
                                sendInfo.comment = (backStackEntry.arguments?.getString("comment") ?: "" )
                                SendConfirmScreen(navController = navController, isBlocked = true)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = this.getSharedPreferences("TON_WALLET", Context.MODE_PRIVATE)
        val isBiometric = sharedPref.getBoolean("BIOMETRIC", false)
        val isCreated = sharedPref.getBoolean("CREATED", false)
        navigateFunction?.invoke(isCreated)
        if (isBiometric)
            biometricFunction?.invoke(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        liteClient.close()
    }
    fun setNavigateFunction(navigate: (Boolean) -> Unit) {
        navigateFunction = navigate
    }
    fun setBiometricFunction(navigate: (Boolean) -> Unit) {
        biometricFunction = navigate
    }
}