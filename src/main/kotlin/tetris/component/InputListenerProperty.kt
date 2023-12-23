package tetris.component

import org.koin.core.context.GlobalContext
import tetris.GameObject
import tetris.PropertyType
import tetris.systems.MoveSystem

data class InputListenerProperty(val gameObject: GameObject) : GameProperty {
    private var activeState: Boolean = true


    override fun getType(): PropertyType {
        return PropertyType.InputProperty
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