package com.example.toncontest.ui.theme.components.main.transaction.send

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.toncontest.R
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import io.github.g00fy2.quickie.QRResult
import io.github.g00fy2.quickie.ScanCustomCode
import io.github.g00fy2.quickie.config.BarcodeFormat
import io.github.g00fy2.quickie.config.ScannerConfig

@Composable
fun ScanButton(navController: NavController, scannedAddress: (String) -> Unit) {
    val scanQrCodeLauncher = rememberLauncherForActivityResult(ScanCustomCode()) { result ->
        when (result) {
            is QRResult.QRSuccess -> {
                scannedAddress(result.content.rawValue)
            }
            is QRResult.QRMissingPermission -> {
                navController.navigate("noCamera")
            }
            else -> null
        }
    }
    Column(
        modifier = Modifier
            .clickable {
                scanQrCodeLauncher.launch(
                    ScannerConfig.build {
                    setBarcodeFormats(listOf(BarcodeFormat.FORMAT_ALL_FORMATS)) // set interested barcode formats
                    setHapticSuccessFeedback(true) // enable (default) or disable haptic feedback when a barcode was detected
                    setShowTorchToggle(true) // show or hide (default) torch/flashlight toggle button
                    setShowCloseButton(true) // show or hide (default) close button
                    setHorizontalFrameRatio(1f) // set the horizontal overlay ratio (default is 1 / square frame)
                    setUseFrontCamera(false) // use the front camera
                })
            }
    ) {
        Row() {
            Icon(
                painter = painterResource(
                    id = R.drawable.scan
                ),
                contentDescription = "Paste",
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp),
                tint = Light_Blue
            )
            Text(
                text = MainStrings.scanButtonText,
                color = Light_Blue,
                fontSize = 14.sp,
                fontFamily = robotoFamily,
                modifier = Modifier
                    .padding(start = 4.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
            )
        }
    }
}