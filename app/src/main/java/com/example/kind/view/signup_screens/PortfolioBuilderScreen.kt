package com.example.kind.view.signup_screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import com.example.kind.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.kind.view.composables.PortfolioTemplateCard
import com.example.kind.view.theme.Typography
import com.example.kind.viewModel.SignupViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import com.example.kind.HomeScreens
import com.example.kind.SignupScreens
import com.example.kind.view.composables.KindCharityCard
import com.example.kind.view.main_screens.CharityScreen
import com.example.kind.viewModel.CharityViewModel

@ExperimentalFoundationApi
@Composable
fun PortfolioBuilderScreen(
    viewModel: SignupViewModel,
) {
    val state by viewModel.charityData.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Build your portfolio with your choice of charities",
            fontWeight = Typography.labelLarge.fontWeight,
            fontSize = Typography.headlineMedium.fontSize,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2), Modifier.height(450.dp), content = {
            state.forEach {item {
                KindCharityCard(
                    Title = it.name,
                    Body = it.desc,
                    iconImage = it.iconImage,
                    onClick = { viewModel.navController.navigate(SignupScreens.Charity.route + "/" + it.id) }
                ) }}
        })
    }
}