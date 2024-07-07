package com.example.tictactoeinfinite

import java.util.LinkedList
import java.util.Queue

data class DataModel (
    val board: List<MutableList<Player?>> = List(3) { MutableList(3) {null} },
    val currentPlayer: Player = Player.X,
    val xMoves: Queue<Pair<Int,Int>> = LinkedList(),
    val oMoves: Queue<Pair<Int,Int>> = LinkedList(),
    val xWins: Int = 0,
    val oWins: Int = 0,
)

enum class Player{
    X,O
}