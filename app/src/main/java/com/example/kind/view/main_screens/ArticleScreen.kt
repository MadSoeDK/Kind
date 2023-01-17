package com.example.kind.view.main_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.kind.view.theme.Typography
import com.example.kind.viewModel.ArticleViewModel
import java.time.ZoneId
import java.util.*

@Composable
fun ArticleScreen(
    viewModel: ArticleViewModel
) {
    val state by viewModel.data.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onBackground)
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            IconButton(
                onClick = { viewModel.navController.popBackStack() },
                modifier = Modifier.zIndex(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            AsyncImage(
                model = state.mainImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .blur(
                        3.dp, 3.dp, edgeTreatment = BlurredEdgeTreatment(
                            RoundedCornerShape(1.dp)
                        )
                    ),
                contentScale = ContentScale.Crop,
            )
            AsyncImage(
                model = state.iconImage,
                contentDescription = null,
                modifier = Modifier
                    .absoluteOffset(y = 20.dp)
                    .size(55.dp)
                    .clip(CircleShape)
                    .border(1.dp, MaterialTheme.colorScheme.outline, CircleShape)
                    .align(alignment = Alignment.BottomCenter),
                contentScale = ContentScale.FillBounds,

                )
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = state.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                textAlign = TextAlign.Left
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 2.5.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Author: ", fontWeight = FontWeight.Bold, fontSize = 14.sp
            )
            Text(text = state.author, fontSize = 14.sp)
            Spacer(modifier = Modifier.padding(horizontal = 20.dp))
            Text(
                text = "Date: ", fontWeight = FontWeight.Bold, fontSize = 14.sp
            )
            Text(
                text = "${
                    state.date.toDate().toInstant().atZone(ZoneId.of("Europe/Paris")).dayOfMonth
                }. " + "${
                    state.date.toDate().toInstant()
                        .atZone(ZoneId.of("Europe/Paris")).month.toString()
                        .subSequence(0, 1)
                }" +
                        "${
                            state.date.toDate().toInstant()
                                .atZone(ZoneId.of("Europe/Paris")).month.toString()
                                .subSequence(0, 3).toString().subSequence(1, 3).toString()
                                .toLowerCase(Locale.ROOT)
                        } " +
                        "${
                            state.date.toDate().toInstant().atZone(ZoneId.of("Europe/Paris")).year
                        }", fontSize = 14.sp
            )

        }
        Spacer(modifier = Modifier.padding(vertical = 15.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Text(
                text = state.paragraf,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify
            )
        }

    }
}

/**
 * ----------------------------------------------------------------:::::::::::::::-
 */

/*
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(20.dp, 25.dp)
    )
    {
        Spacer(modifier = Modifier.padding(20.dp))

        // Charity
        Text(
            text = state.charityName,
            color = Color.Black,
            fontSize = Typography.displayMedium.fontSize,
            textAlign = TextAlign.Left
        )

        // Title
        Text(
            text = state.title,
            color = Color.Black,
            fontWeight = Typography.headlineMedium.fontWeight,
            fontSize = Typography.headlineMedium.fontSize,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.padding(20.dp))

        // Article Text
        Text(
            text = state.paragraf,
            color = Color.Black,
            fontSize = Typography.displayMedium.fontSize,
            textAlign = TextAlign.Center
        )
    }
    }
 */