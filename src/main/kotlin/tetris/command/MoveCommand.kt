package tetris.command

import common.Coord
import tetris.MovementDirection
import tetris.component.PositionProperty

class MoveCommand(val speed: Int, var position: PositionProperty, val direction: MovementDirection) : ICommand {


    override suspend fun execute() {
        position.coordinates = position.coordinates + (direction.coord * speed)
    }

    override suspend fun undo() {
        position.coordinates = position.coordinates - (direction.coord * speed)
    }
}