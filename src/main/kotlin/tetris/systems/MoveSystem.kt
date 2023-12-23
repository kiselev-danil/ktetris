package tetris.systems

import common.Coord
import org.koin.core.context.GlobalContext
import tetris.PropertyType
import tetris.component.DrawProperty
import tetris.component.GameProperty
import tetris.component.MoveProperty
import tetris.component.PositionProperty
import tetris.command.CommandQueue
import tetris.command.MoveCommand
import java.lang.Exception

class MoveSystem : GameSystem {
    var queue: CommandQueue = GlobalContext.get().get()
    var props: MutableList<MoveProperty> = ArrayList()
    override fun addComponent(component: GameProperty) {
        if (component.getType() == PropertyType.MoveProperty) {
            this.props.add(component as MoveProperty)
        }
    }

    override fun run() {
        for (el in props.filter { it.isActive() }) {
            //TODO: Check collision
            val cmd : MoveCommand = MoveCommand(el.speed, getPos(el), el.direction)
            queue.push(cmd)
//            println("cmd pushed")
        }
    }

    private fun getPos(component: MoveProperty): PositionProperty {
        if (component.gameObject.props["position"] != null) {
            if (component.gameObject.props["position"]?.getType() == PropertyType.PositionProperty) {
                return (component.gameObject.props["position"] as PositionProperty)
            } else {
                throw Exception("GameObject must contain Position property")
            }
        } else {
            throw Exception("GameObject must contain Position property")
        }
    }
}