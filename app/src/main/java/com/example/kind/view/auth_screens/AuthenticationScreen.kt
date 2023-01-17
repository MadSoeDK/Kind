package com.example.kind.view.auth_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.KindButtonOutlined
import com.example.kind.view.composables.LoginHeader

@Composable
fun AuthenticationScreen(
    navigateToLogin: () -> Unit,
    navigateToSignup: () -> Unit,
) {
    val openDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text(text = "To be developed...") },
                text = {
                    Text(
                        text = "This feature is not implemented yet",
                        fontWeight = FontWeight.Normal
                    )
                },
                confirmButton = {
                    Button(
                        onClick = { openDialog.value = false }
                    ) {
                        Text("Ok")
                    }
                },
                shape = MaterialTheme.shapes.medium
            )
        }

        LoginHeader("Your Portfolio of Kindness")
        KindButton(onClick = navigateToSignup, textProvider = "Signup")
        Spacer(modifier = Modifier.height(12.dp))
        KindButtonOutlined(
            onClick = {openDialog.value = true}, textProvider = "Continue with Google"
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Login",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable { navigateToLogin() },
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}