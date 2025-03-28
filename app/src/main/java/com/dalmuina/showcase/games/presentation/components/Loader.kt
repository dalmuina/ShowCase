package com.dalmuina.showcase.games.presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.dalmuina.showcase.R
import com.dalmuina.showcase.ui.theme.ShowCaseTheme

@Composable
fun Loader(){
    val circleColors:List<Color> = listOf(
        colorResource(R.color.Loader1),
        colorResource(R.color.Loader2),
        colorResource(R.color.Loader3),
        colorResource(R.color.Loader4),
        colorResource(R.color.Loader5),
        colorResource(R.color.Loader6),
        colorResource(R.color.Loader7),
        colorResource(R.color.Loader8),
        colorResource(R.color.Loader9),
        colorResource(R.color.Loader10),
    )

    val infiniteTransition = rememberInfiniteTransition(label="")
    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue= 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 360,
                easing = LinearEasing
            )
        ), label = ""
    )

    CircularProgressIndicator(
        progress = { 1f },
        modifier = Modifier
            .size(size = 100.dp)
            .rotate(degrees = rotateAnimation)
            .border(
                width = 4.dp,
                brush = Brush.sweepGradient(circleColors),
                shape = CircleShape
            ),
        color = MaterialTheme.colorScheme.background,
        strokeWidth = 1.dp,
        trackColor = ProgressIndicatorDefaults.circularIndeterminateTrackColor,
    )
}

@PreviewLightDark
@Composable
private fun LoaderPreview() {
    ShowCaseTheme {
        Loader()
    }
}
