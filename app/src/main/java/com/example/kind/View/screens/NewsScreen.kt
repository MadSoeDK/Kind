package com.example.kind.View.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kind.R
import com.example.kind.View.composables.CharityHeaderAndSubsectionText
import com.example.kind.View.composables.KindCharityCard
import com.example.kind.View.composables.KindNewsCard
import com.example.kind.ViewModel.ExplorerViewModel

@ExperimentalFoundationApi
@Composable
fun NewsScreen(
    //viewModel: ExplorerViewModel
) {
    CharityHeaderAndSubsectionText(Title = "Charity Updates", Subtitle = "Get latest updates from your charities", Categories = arrayOf("Health", "Disasters", "Climate", "Welfare", "Children Care"))
    Column( modifier = Modifier.fillMaxHeight())
    {
        LazyVerticalGrid(columns = GridCells.Fixed(1), Modifier.height(500.dp).width(500.dp),content = {
            items(10 /*TODO: Needs to be adaptive based on the amount of organizations*/) {i -> KindNewsCard(
                Title = "Krig rammer ukraines ungdom",
                Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                OrganizationIcon = painterResource(id = R.drawable.screenshot20220914071147),
                Category = "War",
                Subcategory = "War never changes"
            )
            }
        })

        //NewsCard()
    }

}

@ExperimentalFoundationApi
@Composable
@Preview(showBackground = true)
fun NewsPreview() {
    NewsScreen()
}