package com.dalmuina.showcase.games.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.dalmuina.showcase.games.presentation.model.GameUi
import com.dalmuina.showcase.games.presentation.model.previewGameUi
import com.dalmuina.showcase.ui.theme.ShowCaseTheme

@Composable
fun CardGame(game: GameUi, onClick:()->Unit){
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(10.dp)
            .shadow(40.dp)
            .clickable { onClick() }
    )   {
        Column {
            MainImage(game.backgroundImage)
        }

    }
}

@PreviewLightDark
@Composable
private fun CardGamePreview(
) {
    ShowCaseTheme {
        CardGame(
            game = previewGameUi
        ) { }
    }
}
