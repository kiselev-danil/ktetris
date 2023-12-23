package tetris.component

import com.googlecode.lanterna.TextColor
import org.koin.core.context.GlobalContext
import tetris.GameObject
import tetris.PropertyType
import tetris.systems.DefaultDrawSystem

data class DrawProperty(
    val parent: GameObject,
    var pattern: Set<Pair<Int, Int>> = mutableSetOf()
) :
    GameProperty {
    private val propertyType = PropertyType.DrawProperty
    private var activeState: Boolean = true
    var bgColor: TextColor = TextColor.ANSI.DEFAULT
    var symbol: Char = ' '
    var patternCenter: Pair<Int, Int>? = null

    init {
        var drawSystem: DefaultDrawSystem = GlobalContext.get().get()
        drawSystem.addComponent(this)
    }

    override fun getType(): PropertyType {
        return propertyType
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