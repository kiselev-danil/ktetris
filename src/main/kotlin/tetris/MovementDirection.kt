package tetris

import common.Coord

enum class MovementDirection(val coord: Coord) {
    Up(Coord(0,-1)), Down(Coord(0,1)), Left(Coord(-1,0)), Right(Coord(1,0))
}