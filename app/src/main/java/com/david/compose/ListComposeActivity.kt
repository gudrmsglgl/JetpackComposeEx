package com.david.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.david.compose.ui.theme.ComposeExTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ListComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeExTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val lazyListState = rememberLazyListState()
                    val coroutineScope = rememberCoroutineScope()
                    Column {
                        TopScrollHandlerButtons(100, lazyListState, coroutineScope)
                        SimpleList(100, lazyListState)
                    }
                }   
            }
        }
    }
}

@Composable
fun TopScrollHandlerButtons(itemSize: Int, scrollState: LazyListState, coroutineScope: CoroutineScope) {

    Row() {
        Button(
            onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }
        ) {
            Text("action Top Scroll")
        }

        Button(
            onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(itemSize.minus(1))
                }
            }
        ) {
            Text("action End Scroll")
        }
    }
}


@Composable
fun SimpleList(itemSize: Int, scrollState: LazyListState) {
    // We save the scrolling position with this state that can also
    // be used to programmatically scroll the list
    //val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(itemSize) {
            ImageListItem(index = it)
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeExTheme {
        val lazyListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        Column {
            TopScrollHandlerButtons(100, lazyListState, coroutineScope)
            SimpleList(100, lazyListState)
        }
    }
}