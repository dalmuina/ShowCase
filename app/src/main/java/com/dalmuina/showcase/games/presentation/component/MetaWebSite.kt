package com.dalmuina.showcase.games.presentation.component

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dalmuina.showcase.ui.theme.ShowCaseTheme


@SuppressLint("UseKtx")
@Composable
fun MetaWebsite(url:String){
    val context = LocalContext.current
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    Column{
        Text(text= "METASCORE",
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 10.dp,bottom = 10.dp))
        Button(onClick = {if (url.isNotBlank() and url.isNotEmpty()) context.startActivity(intent)},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray,
                contentColor = Color.White)
        ){
            Text(text="Sitio Web")
        }
    }
}

@PreviewLightDark
@Composable
private fun MetaWebsitePreview() {
    ShowCaseTheme {
        MetaWebsite(
            url = "url wen"
        )
    }
}
