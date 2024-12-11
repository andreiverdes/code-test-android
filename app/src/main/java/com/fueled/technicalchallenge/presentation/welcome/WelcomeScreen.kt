package com.fueled.technicalchallenge.presentation.welcome

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fueled.technicalchallenge.R
import com.fueled.technicalchallenge.presentation.ui.theme.TechnicalChallengeTypography
import com.fueled.technicalchallenge.presentation.ui.theme.TechnicalChallengeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    onGetStartedClicked: () -> Unit,
) {
    MoviesColumnsBackground()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(Modifier.weight(.25f))
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.welcome_title),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = TechnicalChallengeTypography.displayMedium,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.welcome_subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = TechnicalChallengeTypography.headlineMedium,
            )
            Spacer(Modifier.size(16.dp))
            Button(modifier = Modifier
                .height(54.dp)
                .fillMaxWidth(),
                onClick = onGetStartedClicked,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(stringResource(R.string.welcome_button))
                }
            }
            Spacer(Modifier.size(16.dp))
        }
    }
}

@Composable
private fun MoviesColumnsBackground() {
    val ids by remember {
        mutableStateOf(
            listOf(
                R.drawable.hero_1,
                R.drawable.hero_2,
                R.drawable.hero_3,
                R.drawable.hero_4,
                R.drawable.hero_5,
                R.drawable.hero_6,
                R.drawable.hero_7,
                R.drawable.hero_8,
                R.drawable.hero_9,
                R.drawable.hero_10,
                R.drawable.hero_11,
                R.drawable.hero_12,
                R.drawable.hero_13,
                R.drawable.hero_14,
                R.drawable.hero_15,
                R.drawable.hero_16,
                R.drawable.hero_17,
                R.drawable.hero_18,
                R.drawable.hero_20,
                R.drawable.hero_20,
            )
        )
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val boxWidth = screenWidth * 2f
    val boxHeight = screenHeight * 1.25f

    Box(
        modifier = Modifier.wrapContentSize(unbounded = true),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .size(boxWidth, boxHeight)
                .rotate(25f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val columnsCount by remember { mutableIntStateOf(6) }
            val columnWidth by remember { mutableStateOf(boxWidth * (1 / columnsCount.toFloat()) ) }

            repeat(columnsCount) { index ->
                val listState = rememberLazyListState()
                val coroutineScope = rememberCoroutineScope()

                // Automatically scroll the LazyColumn
                LaunchedEffect(listState.isScrollInProgress) {
                    coroutineScope.launch {
                        while (true) {
                            if (!listState.isScrollInProgress) {
                                listState.animateScrollBy(
                                    50f,
                                    animationSpec = tween(
                                        durationMillis = 1000,
                                        easing = LinearEasing
                                    )
                                )
                            } else {
                                delay(200)
                            }
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .width(columnWidth)
                        .fillMaxHeight()
                        .padding(6.dp),
                    state = listState,
                    reverseLayout = index % 2 == 1,
                ) {
                    items(Int.MAX_VALUE) { itemIndex ->
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(columnWidth * 1.5f)
                                .padding(top = 6.dp, bottom = 6.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            painter = painterResource(ids[(index % 4) * 5 + itemIndex % 5]),
                            contentDescription = "hero_$itemIndex",
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun SignInScreenPreview() {
    TechnicalChallengeTheme {
        WelcomeScreen(
            onGetStartedClicked = { }
        )
    }
}