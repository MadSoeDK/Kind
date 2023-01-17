package com.example.kind.view.main_screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kind.R
import com.example.kind.view.composables.KindNewsCard
import com.example.kind.view.composables.CharityHeaderAndSubsectionText
import com.example.kind.viewModel.CharityCategory

@ExperimentalFoundationApi
@Composable
fun NewsScreen() {
    CharityHeaderAndSubsectionText(
        Title = "Charity Updates",
        Categories = CharityCategory.values()
    )
    Column(modifier = Modifier.fillMaxHeight())
    {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            Modifier
                .height(500.dp)
                .width(500.dp),
            content = {
                items(10 /*TODO: Needs to be adaptive based on the amount of organizations*/) { i ->
                    KindNewsCard(
                        Title = "Krig rammer ukraines ungdom",
                        Body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit...",
                        OrganizationIcon = painterResource(id = R.drawable.bekindsplashart1),
                        Category = "War",
                        Subcategory = "War never changes"
                    )
                }
            })
    }

}