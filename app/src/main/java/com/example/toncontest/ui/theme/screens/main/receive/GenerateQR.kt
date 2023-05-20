package com.example.toncontest.ui.theme.screens.main.receive

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.toncontest.data.ton.account.account
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel


// Пример генерации QR-кода с логотипом с использованием библиотеки ZXing
fun test(context: Context): ImageBitmap {
    val hints = HashMap<EncodeHintType, Any>()
    hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.M
    hints[EncodeHintType.MARGIN] = 0
    var qr = QRCodeWriter2().encode(
        account.address,
        com.google.zxing.BarcodeFormat.QR_CODE,
        768,
        768,
        hints,
        null,
        context
    )
    return qr.asImageBitmap()
}


