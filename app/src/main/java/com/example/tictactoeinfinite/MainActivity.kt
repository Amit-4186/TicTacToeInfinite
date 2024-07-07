package com.example.tictactoeinfinite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoeinfinite.ui.theme.BlueCustom
import com.example.tictactoeinfinite.ui.theme.TicTacToeInfiniteTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : GameViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            TicTacToeInfiniteTheme {
                Scaffold(topBar = {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = BlueCustom,
                        shadowElevation = 6.dp,
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    ) {
                        TopAppBar(
                            title = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "TicTacToe Infinite",
                                    textAlign = TextAlign.Center,
                                    fontSize = 44.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily.Cursive,
                                    color = Color.White
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent
                            )
                        )
                    }
                }
                ) { padding ->
                    GameScreen(viewModel, padding)
                }
            }
        }
    }
}