package com.example.kind.View.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kind.View.composables.HeaderAndTextWithSelectionFilter
import com.example.kind.View.composables.TextFieldColumn
import com.example.kind.ViewModel.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
) {
    val Categories = arrayOf("All", "Personally", "Subscription", "Payment", "Transact")
    Column() {
        HeaderAndTextWithSelectionFilter(
            Title = "Profile info",
            Subtitle = null,
            Categories = Categories
        )
        LazyColumn {
            item {
                for (i in 0 until viewModel.fields.size) {
                    TextFieldColumn(
                        title = Categories[i + 1],
                        fields = viewModel.fields[i],
                        state = viewModel.formState
                    )
                }
            }
            item {
                Button(onClick = { viewModel.onFormSubmit() }) {
                    Text("Submit")
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview() {
    ProfileScreen(viewModel = ProfileViewModel())
}