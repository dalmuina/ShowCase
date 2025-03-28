package com.dalmuina.showcase.games.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dalmuina.showcase.ui.theme.ShowCaseTheme

@Composable
fun MainImage(imageUrl:String?){
    val image = rememberAsyncImagePainter(model = imageUrl)

    Image(painter = image,
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp))
}

@PreviewLightDark
@Composable
private fun MainImagePreview() {
    ShowCaseTheme {
        MainImage("https:Image_url")
    }
}
