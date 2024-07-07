package com.example.tictactoeinfinite

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel: ViewModel() {
    private val _state = MutableStateFlow(DataModel())
    val state : StateFlow<DataModel> = _state

    fun makeMove(cell: Pair<Int,Int>): Int {
        val currentState = _state.value

        val (row,col) = cell

        if(currentState.board[row][col]!=null)
            return 0

        val player = currentState.currentPlayer
        val newBoard = currentState.board.map { it.toMutableList() }.toList()

        newBoard[row][col] = player

        val movesQueue = if (player == Player.X) currentState.xMoves
        else currentState.oMoves

        movesQueue.add(cell)

        if(movesQueue.size>3) {
            val (removeRow, removeCol) = movesQueue.remove()
            newBoard[removeRow][removeCol] = null
        }

        _state.value = currentState.copy(
            board = newBoard,
            currentPlayer = if(player == Player.X) Player.O else Player.X,
            xMoves = if(player == Player.X) movesQueue else currentState.xMoves,
            oMoves = if(player == Player.O) movesQueue else currentState.oMoves
        )

        return checkWinCondition()
    }

    private fun checkWinCondition(): Int {
        val currentState = _state.value
        val currentBoard = currentState.board
        var currentWin : Player? = null
        var output= 0

        if(currentBoard[0][0] == currentBoard[0][1] && currentBoard[0][1] == currentBoard[0][2] && currentBoard[0][0]!=null) {
            currentWin = if (currentBoard[0][0]==Player.X) Player.X else Player.O
            output = 1
        }
        else if(currentBoard[1][0] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[1][2] && currentBoard[1][0]!=null) {
            currentWin = if (currentBoard[0][0]==Player.X) Player.X else Player.O
            output = 2
        }
        else if(currentBoard[2][0] == currentBoard[2][1] && currentBoard[2][2] == currentBoard[2][1] && currentBoard[2][0]!=null) {
            currentWin = if (currentBoard[1][0]==Player.X) Player.X else Player.O
            output = 3
        }
        else if(currentBoard[0][0] == currentBoard[1][0] && currentBoard[1][0] == currentBoard[2][0] && currentBoard[2][0]!=null) {
            currentWin = if (currentBoard[2][0]==Player.X) Player.X else Player.O
            output = 4
        }
        else if(currentBoard[0][1] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[2][1] && currentBoard[0][1]!=null) {
            currentWin = if (currentBoard[0][1]==Player.X) Player.X else Player.O
            output = 5
        }
        else if(currentBoard[0][2] == currentBoard[1][2] && currentBoard[1][2] == currentBoard[2][2] && currentBoard[0][2]!=null) {
            currentWin = if (currentBoard[0][2]==Player.X) Player.X else Player.O
            output = 6
        }
        else if(currentBoard[0][0] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[2][2] && currentBoard[0][0]!=null) {
            currentWin = if (currentBoard[0][0]==Player.X) Player.X else Player.O
            output = 7
        }
        else if(currentBoard[0][2] == currentBoard[1][1] && currentBoard[1][1] == currentBoard[2][0] && currentBoard[0][2]!=null) {
            currentWin = if (currentBoard[0][2]==Player.X) Player.X else Player.O
            output = 8
        }
        if (output!=0) {
            _state.value = currentState.copy(
                xWins = currentState.xWins + if (currentWin==Player.X) 1 else 0 ,
                oWins = currentState.oWins + if (currentWin==Player.O) 1 else 0
            )
        }
        return output
    }

    fun playAgain () {
        val xWins = _state.value.xWins
        val oWins = _state.value.oWins
        _state.value = DataModel()
        _state.value = _state.value.copy(
            xWins = xWins,
            oWins = oWins
        )
    }

    fun reset () {
        _state.value = DataModel()
    }

}