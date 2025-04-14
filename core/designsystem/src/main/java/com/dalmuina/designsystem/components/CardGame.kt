package com.dalmuina.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.dalmuina.designsystem.theme.ShowCaseTheme
import com.dalmuina.model_ui.GameUi

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

internal val previewGameUi = GameUi(
    id = 1,
    name = "GTA V",
    backgroundImage = "https://example_image.jpg"
)


@PreviewScreenSizes
@Composable
private fun CardGamePreview(
) {
    ShowCaseTheme {
        CardGame(
            game = previewGameUi
        ) { }
    }
}
