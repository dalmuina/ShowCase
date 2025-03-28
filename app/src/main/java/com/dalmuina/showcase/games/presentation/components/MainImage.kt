package com.dalmuina.showcase.games.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dalmuina.showcase.R
import com.dalmuina.showcase.ui.theme.ShowCaseTheme

@Composable
fun MainImage(imageUrl:String?){
    if (LocalInspectionMode.current) {
        Image(
            painter = painterResource(R.drawable.preview_image),
            contentDescription = "Preview placeholder",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
    } else {
        AsyncImage(model = imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
        )
    }
}

@PreviewScreenSizes
@Composable
private fun MainImagePreview() {
    ShowCaseTheme {
        MainImage(null)
    }
}
