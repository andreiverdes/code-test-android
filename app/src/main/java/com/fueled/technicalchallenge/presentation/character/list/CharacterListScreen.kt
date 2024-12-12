package com.fueled.technicalchallenge.presentation.character.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
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
    val characters = viewModel.getCharacters().collectAsLazyPagingItems()

    val gridState = rememberLazyGridState( )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
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
                modifier = Modifier.padding(16.dp), // Adds padding around the grid
                columns = GridCells.Fixed(2), // Specifies 3 columns
                state = gridState,
            ) {
                items(characters.itemCount) { index: Int ->
                    characters.get(index)?.let { item ->
                        CharacterCard(
                            modifier = Modifier,
                            data = item,
                            onItemClick = onItemClick,
                        )
                    }
                }
                when (characters.loadState.refresh) {
                    is LoadState.Error -> maxLineSpanItem {
                        Text(
                            text = "Failed to load Heroes :'(",
                            style = MaterialTheme.typography.headlineLarge,
                        )
                    }
                    LoadState.Loading -> maxLineSpanItem {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                    is LoadState.NotLoading -> {}
                }

                when (characters.loadState.append) {
                    is LoadState.Error -> maxLineSpanItem {
                        Text(
                            text = "Failed to load Heroes :'(",
                            style = MaterialTheme.typography.headlineLarge,
                        )
                    }
                    LoadState.Loading -> maxLineSpanItem {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                    is LoadState.NotLoading -> {}
                }
            }
        }
    }
}

fun LazyGridScope.maxLineSpanItem(
    content: @Composable LazyGridItemScope.() -> Unit
) = item(span = { GridItemSpan(this.maxLineSpan) }, content = content)

