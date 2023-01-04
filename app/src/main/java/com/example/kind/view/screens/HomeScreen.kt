package com.example.kind.view.screens

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kind.Screen
import com.example.kind.view.home.composables.HeaderAndText
import com.example.kind.view.theme.*
import com.example.kind.viewModel.HomeViewModel
import com.example.kind.view.composables.KindCard
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    Column {
        HeaderAndText(viewModel.getDonatedAmount(), viewModel.getText())

        Text(text = "Charity Update", color = MaterialTheme.colorScheme.primary, style = Typography.headlineMedium)
        Text("The latest news from your charities")
        LazyRow{
            viewModel.getArticles().forEachIndexed { i,element ->
                item {
                    if (i==0) {Spacer(modifier = Modifier.width(10.dp))}
                    /*if (i==0) {
                        KindCard(
                            modifier = Modifier.padding(),
                            titleProvider = element.header,
                            subTitleProvier = element.header,
                            onClick = { viewModel.navController.navigate("home") }) //TODO: Home for now!
                            }*/
                    KindCard(
                        titleProvider = element.header,
                        subTitleProvier = element.header,
                        onClick = { viewModel.navController.navigate("home") }) //TODO: Home for now!
                }
            }
        }
        

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Explore charities", color = MaterialTheme.colorScheme.secondary, style = Typography.headlineLarge)
        Text("Get to know other charities better")

        LazyRow {
            viewModel.getCharities().forEach {
                item {
                    KindCard(titleProvider = it.name, subTitleProvier = it.name, onClick = { viewModel.navController.navigate(Screen.Charity.route + "/" + it.id.toString()) })
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val viewModel = viewModel<HomeViewModel>()
    HomeScreen(
        viewModel
    )
}