package com.example.kind.View.home.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import com.example.kind.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kind.View.composables.CharityHeaderAndSubsectionText
import com.example.kind.View.composables.KindCharityCard
import com.example.kind.ViewModel.CharityExplorerViewModel

@ExperimentalFoundationApi
@Composable
fun CharityExplorerScreen(
    viewModel: CharityExplorerViewModel
) {
    Column {
        CharityHeaderAndSubsectionText(Title = "Charity Explorer", Subtitle = "Get to know other charities better", Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care"))
        LazyVerticalGrid(cells = GridCells.Fixed(2), content = {
            items(10 /*Needs to be adaptive based on the amount of organizations*/) {i -> KindCharityCard(
                Title = "Red Cross",
                Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147),
                Category = null
            )}
        })
        /*Row() {
            KindCharityCard(Title = "Red Cross", Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...", OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147))
            KindCharityCard(Title = "Doctors", Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...", OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147))
        }
        Row() {
            KindCharityCard(Title = "Red Cross", Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...", OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147))
            KindCharityCard(Title = "Doctors", Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...", OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147))
        }
        Row() {
            KindCharityCard(Title = "Red Cross", Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...", OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147))
            KindCharityCard(Title = "Doctors", Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...", OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147))
        }

         */
    }
}

@ExperimentalFoundationApi
@Composable
@Preview(showBackground = true)
fun CharityExplorerPreview() {
    CharityExplorerScreen(
        CharityExplorerViewModel()
    )
}