package com.example.kind.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.R

@Composable
fun LoginHeader(size: Int) {

    Row {
        /*Column() {
            Spacer(modifier = Modifier.size((size/2).dp))
            Text(text = "be ", fontSize = (size/2).sp, color = primary)
        }

        Text(text = "Kind", fontSize = size.sp, color = primary)*/
        Image(painterResource(id = R.drawable.screenshot20220914071147), contentDescription = "", Modifier.size((size*1.75).dp))
    }

}

@Preview(showBackground = true)
@Composable
fun LoginHeaderPreview() {
    LoginHeader(96)
}