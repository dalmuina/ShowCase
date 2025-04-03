package com.dalmuina.showcase.games.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.dalmuina.showcase.ui.theme.ShowCaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title: String, showBackButton: Boolean = false, onClickBackButton: () -> Unit, onAction:()->Unit){
    TopAppBar(
        title = { Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary),
        navigationIcon = {
            if(showBackButton){
                IconButton(onClick={onClickBackButton()}) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",tint = Color.White)
                }
            }
        },
        actions = {
            if(!showBackButton){
                IconButton(onClick={onAction()}) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "",tint = Color.White)
                }
            }
        }
    )
}

@Preview(widthDp = 200)
@Preview(widthDp = 300)
@Preview(widthDp = 400)
@Preview(widthDp = 500)
@Preview(uiMode = UI_MODE_NIGHT_YES)
annotation class PreviewWidths

@PreviewWidths
@Composable
fun MainTopBarPreviewBack(){
    ShowCaseTheme {
        MainTopBar(
            title = "API GAMES",
            true,
            onClickBackButton = {},
            onAction = {})
    }
}

@PreviewWidths
@Composable
fun MainTopBarPreviewMain(){
    ShowCaseTheme {
        MainTopBar(
            title = "API GAMES",
            false,
            onClickBackButton = {},
            onAction = {})
    }
}
