package com.example.kind.view.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kind.view.theme.Typography
import com.example.kind.viewModel.CharityCategory
import com.example.kind.viewModel.ExplorerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharityHeaderAndSubsectionText(
    Title: String,
    Categories: Array<CharityCategory>,
    viewModel: ExplorerViewModel
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(CharityCategory.All) }

    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 5.dp),
        verticalArrangement = Arrangement.Top, Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = Title,
            fontWeight = Typography.headlineLarge.fontWeight,
            fontSize = Typography.headlineSmall.fontSize,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Categories.forEach {
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
    }
}