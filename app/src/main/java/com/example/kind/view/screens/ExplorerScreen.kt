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
import com.example.kind.ViewModel.ExploreViewModel
import com.example.kind.view.composables.CharityHeaderAndSubsectionText
import com.example.kind.view.composables.KindCharityCard

@ExperimentalFoundationApi
@Composable
fun ExplorerScreen(
    viewModel: ExploreViewModel
) {
    Column {
        //TODO: Seperate categories composable
        CharityHeaderAndSubsectionText(Title = "Charity Explorer", Subtitle = "Get to know other charities better", Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care"))
        LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(500.dp),content = {
            items(10 /*TODO: Needs to be adaptive based on the amount of organizations*/) {i -> KindCharityCard(
                Title = "Red Cross",
                Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147),
                Category = null
            )}
        })
    }
}

@ExperimentalFoundationApi
@Composable
@Preview(showBackground = true)
fun ExplorerPreview() {
    ExplorerScreen(
        ExploreViewModel()
    )
}