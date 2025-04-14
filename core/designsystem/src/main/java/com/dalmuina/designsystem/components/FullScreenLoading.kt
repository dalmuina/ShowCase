package com.dalmuina.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dalmuina.designsystem.theme.ShowCaseTheme

@Composable
fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        contentAlignment = Alignment.Center,

    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun FullScreenLoadingPreview() {
    ShowCaseTheme {
        FullScreenLoading()
    }
}
