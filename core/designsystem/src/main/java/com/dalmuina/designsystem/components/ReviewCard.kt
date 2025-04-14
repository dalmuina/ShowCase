package com.dalmuina.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dalmuina.designsystem.theme.ShowCaseTheme

@Composable
fun ReviewCard(metascore: Int?=null){
    Card(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text= metascore?.toString() ?: "n/a",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize= 45.sp)
        }
    }
}

@PreviewLightDark
@Composable
private fun ReviewCardPreview() {
    ShowCaseTheme {
        ReviewCard(
            metascore = null
        )
    }
}
