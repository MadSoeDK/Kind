package com.example.kind.view.main_screens

import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.kind.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kind.HomeScreens
import com.example.kind.view.composables.CharityHeaderAndSubsectionText
import com.example.kind.view.composables.KindCharityCard
import com.example.kind.viewModel.ExplorerViewModel

@ExperimentalFoundationApi
@Composable
fun ExplorerScreen(
    viewModel: ExplorerViewModel
) {

    val state by viewModel.data.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TODO: Seperate categories composable
        CharityHeaderAndSubsectionText(
            Title = "Charity Explorer",
            Subtitle = "Get to know other charities better",
            Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care")
        )

        if(state.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(500.dp), content = {
                state.forEach {
                    item {
                        KindCharityCard(
                            Title = it.name,
                            Body = it.desc,
                            OrganizationIcon = painterResource(id = R.drawable.bekindsplashart1),
                            onClick = { viewModel.navController.navigate(HomeScreens.Charity.route + "/" + it.id) }
                        )
                    }
                }
            })
        }
    }
}