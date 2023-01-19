package com.example.kind.view.main_screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kind.HomeScreens
import com.example.kind.model.CharityCategory
import com.example.kind.view.composables.CharityHeaderAndSubsectionText
import com.example.kind.view.composables.KindCharityCard
import com.example.kind.view.theme.Typography
import com.example.kind.viewModel.ExplorerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun ExplorerScreen(
    viewModel: ExplorerViewModel
) {
    val state by viewModel.data.collectAsState()

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(CharityCategory.All) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(Unit) {
        viewModel.getCharities()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CharityHeaderAndSubsectionText(
            Title = "Explore the different charities",
        ) {
            CharityCategory.values().forEach {
                Box(
                    modifier = Modifier
                        .clickable {
                            onOptionSelected(it)
                            viewModel.getCharitiesByCategory(it.toString())
                        }
                        .indicatorLine(
                            enabled = it != selectedOption,
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            isError = false,
                            interactionSource = interactionSource,
                            unfocusedIndicatorLineThickness = 2.dp
                        )
                        .absoluteOffset(y = -4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = it.name.replace('_', ' '),
                        fontWeight = if (it != selectedOption) MaterialTheme.typography.bodyLarge.fontWeight else MaterialTheme.typography.bodyMedium.fontWeight,
                        fontSize = Typography.labelLarge.fontSize,
                    )
                }
                Spacer(modifier = Modifier.width(15.dp))
            }
        }
        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        if (state.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(600.dp)) {
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
            }
        }
    }
}