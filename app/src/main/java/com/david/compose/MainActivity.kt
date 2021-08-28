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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.david.compose.ui.theme.ComposeExTheme
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExTheme {
                val isClicked = remember { mutableStateOf(false) }
                PhotographerCard(
                    isClick = isClicked.value,
                    onClicked = {
                        isClicked.value = !isClicked.value
                    }
                )
            }
        }
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier, isClick: Boolean, onClicked: () -> Unit) {
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color = if (isClick) Color.Blue else Color.Transparent)
            .clickable(onClick = onClicked)
            .padding(16.dp)
    ) {
       Surface(
           modifier = Modifier.size(50.dp),
           shape = CircleShape,
           color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
       ) {

       }
       Column(
           modifier = Modifier
               .padding(start = 8.dp)
               .align(Alignment.CenterVertically)
       ) {
           Text("Alfred Sisley", fontWeight = FontWeight.Bold)
           CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
               Text("3 minutes ago", style = MaterialTheme.typography.body2)
           }
       }
    }
}

@Preview(showBackground = true)
@Composable
fun photographerCardPreview() {
    ComposeExTheme {
        val isClicked = remember { mutableStateOf(false) }
        PhotographerCard(
            isClick = isClicked.value,
            onClicked = {
                isClicked.value = !isClicked.value
            }
        )
    }
}