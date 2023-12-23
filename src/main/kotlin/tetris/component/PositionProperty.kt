package tetris.component

import tetris.GameObject
import tetris.PropertyType

data class PositionProperty(val parent: GameObject, var x: Int = 0, var y: Int = 0) : GameProperty {
    private var activeState: Boolean = true

    override fun getType(): PropertyType {
        return PropertyType.PositionProperty
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