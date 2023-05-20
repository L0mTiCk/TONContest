package com.example.toncontest.ui.theme.screens.main.qrcode

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.toncontest.R
import com.example.toncontest.data.Data
import com.example.toncontest.ui.theme.Light_Blue
import com.example.toncontest.ui.theme.robotoFamily
import com.example.toncontest.ui.theme.screens.Loader
import com.example.toncontest.ui.theme.screens.NavBack


@Composable
fun NoCameraScreen(navController: NavController, context: Context) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val permission = Manifest.permission.CAMERA
        val result = ContextCompat.checkSelfPermission(context, permission)
        if (result == PackageManager.PERMISSION_GRANTED) {
            navController.popBackStack()
        }
    }
    @Composable
    fun OpenPermissionButton() {
        Button(
            onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", context.packageName, null)
                intent.data = uri
                launcher.launch(intent)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Light_Blue),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp)
        ) {
            Text(
                text = Data.noCameraButtonText,
                color = Color.White,
                textAlign = TextAlign.Center,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp,
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 14.dp, bottom = 14.dp)
            )
        }
    }
    Scaffold(
        topBar = { NavBack(navController = navController, tint = Color.White) },
        backgroundColor = Color.Black
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.Black)
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(174.dp))
                Loader(R.raw.toobad)
                Text(
                    textAlign = TextAlign.Center,
                    text = Data.noCameraHeaderText,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = robotoFamily,
                    modifier = Modifier
                        .padding(top = 12.dp, end = 12.dp)
                )
                Text(
                    text = Data.noCameraMainText,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = robotoFamily,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(280.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                OpenPermissionButton()
                Spacer(modifier = Modifier.height(117.dp))
            }
        }
    }
}