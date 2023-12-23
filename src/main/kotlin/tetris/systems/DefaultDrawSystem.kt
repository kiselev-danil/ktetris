package tetris.systems

import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.terminal.Terminal
import org.koin.core.context.GlobalContext
import tetris.component.GameProperty
import tetris.PropertyType
import tetris.component.PositionProperty
import tetris.component.DrawProperty
import java.lang.Exception

class DefaultDrawSystem : GameSystem {
    var props: MutableList<DrawProperty> = ArrayList()
    private var defaultBGColor: TextColor = GlobalContext.get().get()
    private val terminal: Terminal = GlobalContext.get().get()
    override fun addComponent(component: GameProperty) {
        if (component.getType() == PropertyType.DrawProperty) {
            this.props.add(component as DrawProperty)
        } else {
            terminal.setCursorPosition(0, 0)
            terminal.putString("ERROR")
        }
    }

    override fun run() {
        terminal.clearScreen()
        terminal.flush()
        var position: Pair<Int, Int>
        for (component in props.filter { it.isActive() }) {
            position = getPos(component)
            terminal.setBackgroundColor(component.bgColor)
            val cursorOffset: Pair<Int, Int> = Pair(
                (component.patternCenter?.first ?: 0) + position.first,
                (component.patternCenter?.second ?: 0) + position.second
            )
            component.pattern.forEach { pair ->
                terminal.setCursorPosition(
                    pair.first + cursorOffset.first, pair.second + cursorOffset.second
                )
                terminal.putCharacter(component.symbol)
            }
            terminal.setBackgroundColor(defaultBGColor)
            terminal.flush()
        }
    }

    private fun getPos(component: DrawProperty): Pair<Int, Int> {
        if (component.parent.props["position"] != null) {
            if (component.parent.props["position"]?.getType() == PropertyType.PositionProperty) {
                return Pair((component.parent.props["position"] as PositionProperty).coordinates.x, (component.parent.props["position"] as PositionProperty).coordinates.y)
            } else {
                throw Exception("GameObject must contain Position property")
            }
        } else {
            throw Exception("GameObject must contain Position property")
        }
    }

}