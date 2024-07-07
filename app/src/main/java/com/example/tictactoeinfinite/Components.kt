package com.example.tictactoeinfinite

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictactoeinfinite.ui.theme.Aqua
import com.example.tictactoeinfinite.ui.theme.GreenishYellow

@Composable
fun BoardBase() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(10.dp)
    ) {
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 1 / 3, y = 0f),
            end = Offset(x = size.width * 1 / 3, y = size.height)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = size.width * 2 / 3, y = 0f),
            end = Offset(x = size.width * 2 / 3, y = size.height)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 1 / 3),
            end = Offset(x = size.width, y = size.height * 1 / 3)
        )
        drawLine(
            color = Color.Gray,
            strokeWidth = 5f,
            cap = StrokeCap.Round,
            start = Offset(x = 0f, y = size.height * 2 / 3),
            end = Offset(x = size.width, y = size.height * 2 / 3)
        )
    }
}

@Composable
fun Cross() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp)
    ) {
        drawLine(
            color = GreenishYellow,
            strokeWidth = 20f,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = size.height)
        )
        drawLine(
            color = GreenishYellow,
            strokeWidth = 20f,
            start = Offset(x = 0f, y = size.height),
            end = Offset(x = size.width, y = 0f)
        )
    }
}

@Composable
fun Circle() {
    Canvas(
        modifier = Modifier
            .size(60.dp)
            .padding(5.dp)
    ) {
        drawCircle(
            color = Aqua,
            style = Stroke(width = 20f)
        )
    }
}

@Composable
fun WinningLine(winNumber: Int) {
    Canvas(
        modifier = Modifier.size(300.dp)
    ) {
        when (winNumber) {
            1 -> drawLine(
                color = Color.Red,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = 0f, y = size.height * 1 / 6),
                end = Offset(x = size.width, y = size.height * 1 / 6)
            )

            2 -> drawLine(
                color = Color.Red,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = 0f, y = size.height * 1 / 2),
                end = Offset(x = size.width, y = size.height * 1 / 2)
            )

            3 -> drawLine(
                color = Color.Red,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = 0f, y = size.height * 5 / 6),
                end = Offset(x = size.width, y = size.height * 5 / 6)
            )

            4 -> drawLine(
                color = Color.Red,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = size.width * 1 / 6, y = 0f),
                end = Offset(x = size.width * 1 / 6, y = size.height)
            )

            5 -> drawLine(
                color = Color.Red,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = size.width * 3 / 6, y = 0f),
                end = Offset(x = size.width * 3 / 6, y = size.height)
            )

            6 -> drawLine(
                color = Color.Red,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = size.width * 5 / 6, y = 0f),
                end = Offset(x = size.width * 5 / 6, y = size.height)
            )

            7 -> drawLine(
                color = Color.Red,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = size.height)
            )

            8 -> drawLine(
                color = Color.Red,
                strokeWidth = 10f,
                cap = StrokeCap.Round,
                start = Offset(x = 0f, y = size.height),
                end = Offset(x = size.width, y = 0f)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CrossPreview() {
    Box(
        modifier = Modifier
            .size(300.dp)
            .background(Color.White)
            .shadow(elevation = 4.dp),

        ) {
        BoardBase()
        Cross()
        Circle()
        WinningLine(winNumber = 1)
        WinningLine(winNumber = 4)
    }
}