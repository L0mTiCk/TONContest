import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toncontest.data.main.MainStrings
import com.example.toncontest.data.main.network.checkConnection
import com.example.toncontest.ui.theme.robotoFamily
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopBarConnection(alpha: Float, context: Context, updated: (Boolean) -> Unit) {

    var connecting by remember { mutableStateOf(false) }
    var updating by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf( MainStrings.topBarNetwork) }
    var isVisible by remember { mutableStateOf(false) }

    if (connecting)
        text = MainStrings.topBarConnection
    if (updating)
        text = MainStrings.topBarUpdate

    LaunchedEffect(key1 = Unit) {
        delay(300)
        isVisible = true
    }
    checkConnection(context = context, connected = {it -> updating = it})
    LaunchedEffect(key1 = updating) {
        if (updating) {
            delay(500)
            updated(true)
        }
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Row(
            modifier = Modifier
                .height(56.dp)
                .alpha(alpha)
        ) {
            Column(modifier = Modifier.padding(start = 16.dp, top = 7.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AnimatedContent(targetState = text) {
                        Text(
                            text = text,
                            color = Color.White,
                            fontFamily = robotoFamily,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}