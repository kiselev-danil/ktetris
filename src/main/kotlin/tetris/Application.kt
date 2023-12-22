package tetris

import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.terminal.Terminal
import di.commonDIModule
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import tetris.component.PositionProperty
import tetris.component.DrawProperty
import tetris.systems.DefaultDrawSystem
import tetris.systems.GameSystem


open class Application {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {

            startKoin {
                modules(commonDIModule)
            }

            println("Hello World!")
            println("Program arguments: ${args.joinToString()}")
            val terminal: Terminal = GlobalContext.get().get()
            println(terminal.terminalSize)

            val circlePattern: Set<Pair<Int, Int>> =
                setOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(2, 1), Pair(2, 2), Pair(1, 2), Pair(0, 2), Pair(0, 1))

            var drawSystem: DefaultDrawSystem = GlobalContext.get().get()
            val gameObject = GameObject(null)
            var position = PositionProperty(gameObject, Pair(10, 5))
            var drawProperty = DrawProperty(gameObject, circlePattern)
            drawProperty.bgColor = TextColor.ANSI.BLUE
            gameObject.addProperty("drawable", drawProperty)
            gameObject.addProperty("position", position)
//            gameObject.props["drawable"]?.let { drawSystem.addComponent(it) }

            terminal.clearScreen()
            var isRunning = true
            terminal.enterPrivateMode()
            launch { drawCorut(drawSystem) }
            while (isRunning) {
                drawSystem.run()
                if (position.value.first < 30) {
                    position.value = Pair(position.value.first + 1, position.value.second)
                    println(position.value)
                } else {
                    drawProperty.turnOff()
                }
                println(position.value)
                Thread.sleep(100)
            }
            terminal.exitPrivateMode()

        }

        private suspend fun drawCorut(sys: GameSystem) {
            // launch a new coroutine and continue
            while (true) {
                delay(16L) // non-blocking delay for 1 second (default time unit is ms)
                sys.run()
            }

        }

    }
}