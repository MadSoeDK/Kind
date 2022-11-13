package com.example.kind.view.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.view.theme.KindTheme
import com.example.kind.view.theme.primary

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KindTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Header("Kind", 48)
                }
            }
        }
    }
}

@Composable
fun Header(message: String, size: Int) {

    Row {
        Column() {
            Spacer(modifier = Modifier.size((size/2).dp))
            Text(text = "be ", fontSize = (size/2).sp, color = primary)
        }
        
        Text(text = "$message", fontSize = size.sp, color = primary)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KindTheme {
        Header("Kind", 48)
    }
}