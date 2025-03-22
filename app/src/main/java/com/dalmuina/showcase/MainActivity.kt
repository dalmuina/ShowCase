package com.dalmuina.showcase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.dalmuina.showcase.core.presentation.util.ObserveAsEvents
import com.dalmuina.showcase.core.presentation.util.toString
import com.dalmuina.showcase.games.presentation.navigation.NavManager
import com.dalmuina.showcase.games.presentation.GameListEvent
import com.dalmuina.showcase.games.presentation.viewmodel.GamesViewModel
import com.dalmuina.showcase.ui.theme.ShowCaseTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShowCaseTheme {
                Surface(
                    modifier = Modifier.Companion.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager()
                }
            }
        }
    }
}
