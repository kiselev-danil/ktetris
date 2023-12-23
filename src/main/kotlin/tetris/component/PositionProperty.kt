package tetris.component

import common.Coord
import tetris.GameObject
import tetris.PropertyType

data class PositionProperty(val parent: GameObject, var coordinates: Coord = Coord(0,0)) : GameProperty {
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