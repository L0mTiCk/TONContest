package com.example.toncontest.ui.theme.components.main.transaction.send

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.ui.theme.components.main.CardTitle

@Composable
fun SendCardHeader(navController: NavController) {
    Row(
        modifier = Modifier.height(56.dp).offset(x = (-20).dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back arrow"
            )
        }
        CardTitle(text = MainStrings.sendScreenTitle)
    }
}