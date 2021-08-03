package com.david.compose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.david.compose.ui.theme.ComposeExTheme
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    ComposeExTheme {
        Surface(color = Color.Yellow) {
            content.invoke()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) { "Hello Android #$it" }) {
    val count = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names = names, modifier = Modifier.weight(1f))
        Counter(
            num = count.value,
            updateNum = { newValue ->
                count.value = newValue
            }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(text = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Greeting(text: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if(isSelected) Color.Red else Color.Transparent)

    Text(
        text = text,
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable { isSelected = !isSelected }
    )
}

@Composable
fun Counter(num: Int, updateNum: (Int) -> Unit) {
    Button(
        onClick = { updateNum(num+1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (num > 5) Color.Green else Color.White
        )
    ) {
       Text(text = "I'v been clicked $num times")
    }
}



@Preview(showBackground = true, name = "MyApp_Preview")
@Composable
fun MyAppPreview() {
    MyApp {
        MyScreenContent()
    }
}