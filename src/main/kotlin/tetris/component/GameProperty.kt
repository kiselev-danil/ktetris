package tetris.component

import tetris.PropertyType

interface GameProperty {
    fun getType(): PropertyType
    fun turnOn()
    fun turnOff()
    fun isActive(): Boolean
}