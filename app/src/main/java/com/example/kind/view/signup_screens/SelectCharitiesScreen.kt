package com.example.kind.view.signup_screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Indication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kind.model.CharityCategory
import com.example.kind.view.composables.CharityHeaderAndSubsectionText
import com.example.kind.view.theme.Typography
import com.example.kind.viewModel.SignupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun SelectCharitiesScreen(
    viewModel: SignupViewModel
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(CharityCategory.All) }
    val interactionSource = remember { MutableInteractionSource() }

    val charityState by viewModel.charityData.collectAsState()

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

        if (charityState.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(600.dp)) {
                charityState.forEachIndexed { i, it ->
                    item {
                        SelectableCharityCard(
                            Title = it.name,
                            Body = it.desc,
                            iconImage = it.iconImage,
                            selected = it.inPortfolio,
                            onClick = {
                                viewModel.updateSelectedCharities(charityState.mapIndexed { j, item ->
                                    if (i == j)
                                        item.copy(inPortfolio = !item.inPortfolio)
                                    else
                                        item
                                })
                            }
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun SelectableCharityCard(
    Title: String,
    Body: String,
    iconImage: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .width(250.dp)
            .height(175.dp)
            .clickable { onClick() }
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = iconImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape),
                    contentScale = ContentScale.FillBounds,
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = Title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = Typography.headlineSmall.fontSize.times(0.65),
                    color = Typography.headlineLarge.color
                )
            }
            Row(modifier = Modifier.padding(5.dp, 5.dp)) {
                Text(
                    text = Body,
                    fontWeight = Typography.displayMedium.fontWeight,
                    fontSize = Typography.displayMedium.fontSize.times(0.85),
                    color = Typography.displayMedium.color,
                )
            }
        }
    }
}