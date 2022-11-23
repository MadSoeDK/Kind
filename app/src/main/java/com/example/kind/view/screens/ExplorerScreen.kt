package com.example.kind.view.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.kind.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kind.Screen
import com.example.kind.view.composables.CharityHeaderAndSubsectionText
import com.example.kind.view.composables.KindCharityCard
import com.example.kind.ViewModel.ExplorerViewModel
import com.example.kind.ViewModel.HomeViewModel
import com.example.kind.view.theme.size

@ExperimentalFoundationApi
@Composable
fun ExplorerScreen(
    viewModel: ExplorerViewModel
) {
    Column {
        //TODO: Seperate categories composable
        CharityHeaderAndSubsectionText(
            Title = "Charity Explorer",
            Subtitle = "Get to know other charities better",
            Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care")
        )

        LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(500.dp), content = {
            viewModel.getCharities().forEach {
                item {
                    KindCharityCard(
                        Title = it.name,
                        Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                        OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147),
                        onClick = { viewModel.navController.navigate(Screen.Charity.route + "/" + it.id) }
                    )
                }
            }
        })
    }
}

@ExperimentalFoundationApi
@Composable
@Preview(showBackground = true)
fun ExplorerPreview() {
    val viewModel = viewModel<ExplorerViewModel>()
    ExplorerScreen(
        viewModel
    )
}