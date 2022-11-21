package com.example.kind.view.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.R
import com.example.kind.view.theme.KindTheme
import com.example.kind.view.theme.primary

class LoginHeader : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KindTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Header( 48)
                }
            }
        }
    }
}

@Composable
fun Header(size: Int) {

    Row {
        Column() {
            Spacer(modifier = Modifier.size((size/2).dp))
            Text(text = "be ", fontSize = (size/2).sp, color = primary)
        }
        
        Text(text = "Kind", fontSize = size.sp, color = primary)
        Image(painterResource(id = R.drawable.screenshot20220914071147), contentDescription = "", Modifier.size((size*1.75).dp))
    }
    
}

@Preview(showBackground = true)
@Composable
fun LoginHeaderPreview() {
    KindTheme {
        Header( 96)
    }
}