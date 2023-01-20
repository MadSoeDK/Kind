package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.HeaderAndText
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.KindButtonOutlined
import com.example.kind.viewModel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onLogout: () -> Unit,
    transactionHistory: () -> Unit
) {

    val updateChangesOpenDialog = remember { mutableStateOf(false) }
    val deleteAccountOpenDialog = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HeaderAndText(
            headerProvider = "Account Settings",
            textProvider = "Edit your personal settings below"
        )

        if(viewModel.isLoading) {
            CircularProgressIndicator()
        } else {
            Form(state = viewModel.formState, fields = viewModel.fields)
        }

        if (viewModel.fieldReadOnly) {
            KindButton(
                onClick = {
                    viewModel.fieldReadOnly = false
                    viewModel.toggleFieldsReadState()
                },
                textProvider = "Change email and password"
            )
        } else {
            KindButton(
                onClick = {
                    viewModel.fieldReadOnly = true
                    updateChangesOpenDialog.value = true
                },
                textProvider = "Save Changes"
            )
        }

        Button(
            onClick = {
                deleteAccountOpenDialog.value = true
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            ),
            modifier = Modifier.width(280.dp),
        ) {
            Text("Delete User")
        }

        KindButtonOutlined(onClick = transactionHistory, "Transaction history")
        KindButton(onClick = onLogout, "Log Out")


        // Dialogs
        if (updateChangesOpenDialog.value) {
            AlertDialog(
                onDismissRequest = { updateChangesOpenDialog.value = false },
                title = { Text(text = "Update User Details") },
                text = { Text(text = "Are you sure you want to update your user details?") },
                confirmButton = {
                    Button(
                        onClick = {
                            updateChangesOpenDialog.value = false
                            viewModel.onFormSubmit()
                        }
                    ) {
                        Text("Accept")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        updateChangesOpenDialog.value = false
                    }) {
                        Text("Dismiss")
                    }
                }
            )
        }

        var text by remember { mutableStateOf("") }

        if (deleteAccountOpenDialog.value) {
            AlertDialog(
                onDismissRequest = { updateChangesOpenDialog.value = false },
                title = { Text(text = "Delete account") },
                text = {
                    Column {
                        Text(text = "Are you sure you want to delete your account? This cannot be undone. Input your password below")
                        Spacer(modifier = Modifier.padding(vertical = 15.dp))
                        TextField(value = text, onValueChange = {text = it}, singleLine = true, visualTransformation = PasswordVisualTransformation(), label = {Text("Password")})
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            deleteAccountOpenDialog.value = false
                            viewModel.onDeleteUser(text)
                        }
                    ) {
                        Text("Delete account")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        deleteAccountOpenDialog.value = false
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
