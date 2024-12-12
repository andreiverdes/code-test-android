package com.fueled.technicalchallenge.presentation.character.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.fueled.technicalchallenge.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel = viewModel(
        factory = CharacterDetailsViewModel.Factory
    ),
    characterId: Int,
    onBackClicked: () -> Unit,
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCharacterDetails(characterId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn (
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.33f)
                        .padding(bottom = 32.dp),
                    contentAlignment = Alignment.BottomStart,
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = rememberAsyncImagePainter(state.character.fullImageUrl),
                        contentDescription = "Character Image",
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                    )
                    Column {
                        if (isSystemInDarkTheme().not()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(.25f)
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color(0xCCFFFFFF), // Color with 0% opacity
                                                Color(0x00181A20) // Color with 100% opacity
                                            )
                                        )
                                    )
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(.25f))
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(.75f)
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0x00181A20), // Color with 0% opacity
                                            Color(0xFF181A20) // Color with 100% opacity
                                        )
                                    )
                                )
                        )
                    }
                    Text(
                        modifier = Modifier.padding(32.dp),
                        text = state.character.name,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White,
                    )
                }
            }
            item {
                Column {
                    Text(
                        modifier = Modifier.padding(start = 32.dp, bottom = 8.dp, end = 32.dp),
                        text = stringResource(R.string.character_details_label_description),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        modifier = Modifier.padding(start = 32.dp, end = 32.dp),
                        text = state.character.description.ifEmpty { "Unfortunately no description provided :'(" },
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        }
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = "Back arrow",
                        tint = Color.White,
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
            )
        )
    }
}