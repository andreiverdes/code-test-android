package com.fueled.technicalchallenge.presentation.character.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fueled.technicalchallenge.data.model.CharacterApiModel
import com.fueled.technicalchallenge.di.AppModule
import com.fueled.technicalchallenge.domain.model.Character
import com.fueled.technicalchallenge.presentation.character.list.components.CharacterCard

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun CharacterListScreen(
    viewModel: CharacterListViewModel = viewModel(
        factory = CharacterListViewModel.Factory
    ),
    onItemClick: (Character) -> Unit,
) {
    val state by viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            CenterAlignedTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        text = "All Your Heroes!",
                    )
                },
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Specifies 3 columns
                modifier = Modifier.padding(16.dp), // Adds padding around the grid
            ) {
                items(state.characters.size) { index: Int ->
                    CharacterCard(
                        modifier = Modifier,
                        data = state.characters[index],
                        onItemClick = onItemClick,
                    )
                }
            }
        }
    }
}
