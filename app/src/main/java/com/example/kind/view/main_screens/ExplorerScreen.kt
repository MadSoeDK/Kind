package com.example.kind.view.main_screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kind.HomeScreens
import com.example.kind.view.composables.CharityHeaderAndSubsectionText
import com.example.kind.view.composables.KindCharityCard
import com.example.kind.viewModel.CharityCategory
import com.example.kind.viewModel.ExplorerViewModel

@ExperimentalFoundationApi
@Composable
fun ExplorerScreen(
    viewModel: ExplorerViewModel
) {
    val state by viewModel.data.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCharities()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharityHeaderAndSubsectionText(
            Title = "Charity Explorer",
            Categories = CharityCategory.values(),
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        if (state.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(800.dp), content = {
                state.forEach {
                    item {
                        KindCharityCard(
                            Title = it.name,
                            Body = it.desc,
                            iconImage = it.iconImage,
                            onClick = {
                                viewModel.navController.navigate(HomeScreens.Charity.route + "/" + it.id)
                            }
                        )
                    }
                }
            })
        }
    }
}