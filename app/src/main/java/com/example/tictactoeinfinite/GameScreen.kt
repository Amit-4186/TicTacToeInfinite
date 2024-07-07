package com.example.tictactoeinfinite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoeinfinite.ui.theme.Aqua
import com.example.tictactoeinfinite.ui.theme.BlueCustom
import com.example.tictactoeinfinite.ui.theme.GrayBackground
import com.example.tictactoeinfinite.ui.theme.GreenishYellow

@Composable
fun GameScreen(viewModel: GameViewModel, paddingValues: PaddingValues) {
    var currentStatus by rememberSaveable {
        mutableIntStateOf(0)
    }
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .background(color = Aqua, shape = RoundedCornerShape(10.dp))
                    .weight(1f)
                    .padding(8.dp)
                    .height(32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "O:   ", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = state.oWins.toString(), fontSize = 22.sp, color = Color.White)
            }
            Row(
                modifier = Modifier
                    .background(color = GreenishYellow, shape = RoundedCornerShape(10.dp))
                    .weight(1f)
                    .padding(8.dp)
                    .height(32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "X:  ", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = state.xWins.toString(), fontSize = 22.sp, color = Color.White)
            }
        }
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp),
                )
                .clip(RoundedCornerShape(20.dp))
                .background(GrayBackground),
            contentAlignment = Alignment.Center
        ) {
            BoardBase()
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f)
            ) {
                state.board.forEachIndexed { rowIndex, row ->
                    row.forEachIndexed { colIndex, cell ->
                        item {
                            Column(
                                Modifier
                                    .fillMaxWidth(0.9f)
                                    .aspectRatio(1f)
                                    .clickable(indication = null,
                                        interactionSource = remember { MutableInteractionSource() }) {
                                        if (currentStatus == 0) {
                                            currentStatus =
                                                viewModel.makeMove(Pair(rowIndex, colIndex))
                                        }
                                    },
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                if (cell == Player.X) Cross() else if (cell == Player.O) Circle()
                            }
                        }
                    }
                }
            }
            if (currentStatus != 0) WinningLine(winNumber = currentStatus)
        }

        Text(
            text = "Player '${state.currentPlayer}'  Turn",
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Button(
                onClick = {
                    viewModel.playAgain()
                    currentStatus = 0
                },
                elevation = ButtonDefaults.buttonElevation(5.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueCustom,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Play Again", fontSize = 16.sp)
            }

            Button(
                onClick = {
                    viewModel.reset()
                    currentStatus = 0
                },
                elevation = ButtonDefaults.buttonElevation(5.dp),
                shape = RoundedCornerShape(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlueCustom,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Reset Score", fontSize = 16.sp)
            }
        }
    }
}