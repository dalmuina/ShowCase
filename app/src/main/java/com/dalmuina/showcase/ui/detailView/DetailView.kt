package com.dalmuina.showcase.ui.detailView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dalmuina.designsystem.components.MainImage
import com.dalmuina.designsystem.components.MainTopBar
import com.dalmuina.designsystem.components.MetaWebsite
import com.dalmuina.designsystem.components.ReviewCard
import com.dalmuina.designsystem.theme.ShowCaseTheme
import com.dalmuina.designsystem.theme.primaryContainerDark
import com.dalmuina.model_ui.GameDetailUi
import com.dalmuina.showcase.ui.UiViewModel
import com.dalmuina.showcase.ui.ListAction
import com.dalmuina.utils.ObserveEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailViewWrapper(
    viewModel: UiViewModel,
    id: Int,
    modifier: Modifier = Modifier,
    onClickBack:()->Unit
) {
    ObserveEvents(viewModel.events)
    val detail by viewModel.detail.collectAsStateWithLifecycle()

    DetailViewScreen(
        detail = detail,
        modifier = modifier,
        id = id,
        onAction = { action ->
            when (action) {
                is ListAction.OnLoadGameDetail -> {
                    viewModel.onAction(action)
                }
                ListAction.OnBackButtonClick -> {
                    viewModel.onAction(action)
                    onClickBack
                }
                else -> Unit
            }
        }
    )
}

@Composable
fun DetailViewScreen(detail: GameDetailState,
                     modifier: Modifier,
                     id: Int,
                     onAction:(ListAction)->Unit
){
    LaunchedEffect(Unit) {
        onAction(ListAction.OnLoadGameDetail(id))
    }

    if (detail.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            modifier = modifier,
            topBar = {
                MainTopBar (
                    title = detail.gameDetailUi.name,
                    showBackButton = true,
                    onClickBackButton = { onAction(ListAction.OnBackButtonClick) },
                    onAction = {})
            }
        ) {
            ContentDetailView(it, detail)
        }
    }
}

@Composable
fun ContentDetailView(pad: PaddingValues, detail: GameDetailState) {

    Column(
        modifier = Modifier
            .padding(pad)
            .background(primaryContainerDark)
    ) {
        with(detail.gameDetailUi) {
            MainImage(imageUrl = backgroundImage)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 5.dp)
            ) {
                MetaWebsite(website)
                ReviewCard(metacritic)
            }

            val scroll = rememberScrollState(0)
            Text(
                text = detail.gameDetailUi.descriptionRaw,
                color = Color.White,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp, bottom = 10.dp)
                    .verticalScroll(scroll)
            )
        }

    }
}

@PreviewScreenSizes
@Composable
fun PreviewDetailView(){
    ShowCaseTheme {
        val mockGameDetail = GameDetailState(
            isLoading = false,
            gameDetailUi = GameDetailUi(
                name = "Gta",
                descriptionRaw = "This is a description of the GTA game, this is a " +
                        "description of the " +
                        "GTA game, this is a description of the GTA game, this is a " +
                        "description of the GTA game",
                metacritic = 100,
                website = "www.google.com",
                backgroundImage = "https://media-rockstargames-com.akamaized.net/mfe6/prod" +
                        "/__common/img/71d4d17edcd49703a5ea446cc0e588e6.jpg")
            )
        ContentDetailView(PaddingValues(16.dp), mockGameDetail)
    }
}
