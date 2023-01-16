package com.example.kind.view.main_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.Form
import com.example.kind.view.composables.HeaderAndText
import com.example.kind.view.composables.HeaderAndText
import com.example.kind.view.composables.KindButton
import com.example.kind.view.composables.KindButtonDanger
import com.example.kind.viewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onLogout: () -> Unit
) {
    val updateChangesOpenDialog = remember { mutableStateOf(false) }
    val deleteAccountOpenDialog = remember { mutableStateOf(false) }
    val reAuthOpenDialog = remember { mutableStateOf(false) }
    var read by remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!reAuthOpenDialog.value) {
            HeaderAndText(
                headerProvider = "Account Settings",
                textProvider = "Edit your personal settings below"
            )
            Form(
                state = viewModel.formState,
                fields = viewModel.fields.subList(0, 2),
            )
            if (read) {
                KindButton(onClick = {
                    read = !read
                    viewModel.updateChanges()
                },
                "Update changes")
            }
            if (!read) {
                Button(onClick = {
                    updateChangesOpenDialog.value = true
                }) {
                    if (updateChangesOpenDialog.value == true) {
                        AlertDialog(
                            onDismissRequest = {
                                updateChangesOpenDialog.value = false
                                viewModel.updateChanges()
                            },
                            title = { Text(text = "Update User Details") },
                            text = { Text(text = "Are you sure you want to update your user details?") },
                            confirmButton = {
                                Button(onClick = {
                                    updateChangesOpenDialog.value = false
                                    if (!viewModel.fields.isEmpty()) {
                                        viewModel.saveChanges()
                                    }
                                    viewModel.updateChanges()
                                }) {
                                    Text("Accept")
                                }
                            },
                            dismissButton = {
                                Button(onClick = {
                                    updateChangesOpenDialog.value = false
                                    viewModel.updateChanges()
                                    read = !read
                                }) {
                                    Text("Dismiss")
                                }
                            }
                        )
                    }
                    Text("Save Changes")
                }
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
                if (deleteAccountOpenDialog.value == true) {
                    AlertDialog(
                        onDismissRequest = {
                            deleteAccountOpenDialog.value = false
                            viewModel.updateChanges()
                        },
                        title = { Text(text = "Delete your account") },
                        text = { Text(text = "Are you sure you want to delete your account?") },
                        confirmButton = {
                            Button(onClick = {
                                reAuthOpenDialog.value = true
                                deleteAccountOpenDialog.value = false
                            }) {
                                Text("Accept")
                            }
                        },
                        dismissButton = {
                            Button(onClick = {
                                deleteAccountOpenDialog.value = false
                                viewModel.updateChanges()
                            }) {
                                Text("Dismiss")
                            }
                        }
                    )
                }
                Text("Delete User")
            }
            Button(onClick = onLogout) {
                Text(text = "Logout")
            }
        } else {
            ReSignIn(viewModel = viewModel, onLogout)
        }
    }

}

@Composable
fun ReSignIn(viewModel: ProfileViewModel, onLogout: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderAndText(
            headerProvider = "Confirmation",
            textProvider = "Please confirm your user details to delete your account."
        )
        Form(
            state = viewModel.formState,
            fields = viewModel.fields.subList(2, 4),
        )
        Button(onClick = {
            viewModel.deleteUser()
            onLogout()
        }) {
            Text("Confirm")
        }
    }
}
