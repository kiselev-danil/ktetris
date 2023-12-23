package tetris.component

import org.koin.core.context.GlobalContext
import tetris.GameObject
import tetris.MovementDirection
import tetris.PropertyType
import tetris.systems.MoveSystem

data class MoveProperty(val gameObject: GameObject, var direction: MovementDirection = MovementDirection.Down) :
    GameProperty {
    var speed: Int = 1
    private var activeState: Boolean = true

    init {
        val system : MoveSystem = GlobalContext.get().get()
        system.addComponent(this)
    }

    override fun getType(): PropertyType {
        return PropertyType.MoveProperty
    }

    override fun turnOn() {
        activeState = true;
    }

    override fun turnOff() {
        activeState = false
    }

    override fun isActive(): Boolean {
        return activeState
    }
}