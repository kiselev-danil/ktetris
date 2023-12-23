package tetris

import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.terminal.Terminal
import common.Coord
import di.commonDIModule
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.context.GlobalContext
import org.koin.core.context.startKoin
import tetris.component.PositionProperty
import tetris.component.DrawProperty
import tetris.component.MoveProperty
import tetris.systems.DefaultDrawSystem
import tetris.systems.MoveSystem
import tetris.command.CommandQueue
import kotlin.concurrent.thread


open class Application {
    companion object {
        var isRunning = true

        @JvmStatic
        fun main(args: Array<String>) = runBlocking {
            startKoin {
                modules(commonDIModule)
            }

            println("Hello World!")
            println("Program arguments: ${args.joinToString()}")
            val terminal: Terminal = GlobalContext.get().get()
            println(terminal.terminalSize)
            prepareObject()
            val queue: CommandQueue = GlobalContext.get().get()
            terminal.clearScreen()

            runInputDaemon()
            launch { runSys() }
            val gameCycleResult = async {
                while (isRunning) {
                    delay(1000L)
                    queue.execute()
                }
                println("That's end folks")
                terminal.clearScreen()
                terminal.close()

            }
            gameCycleResult.await()
            return@runBlocking
        }

        private fun runInputDaemon() {
            thread(start = true, isDaemon = true, name = "inputDaemon", block = {
                var ch: Char = ' '
                while (isRunning) {
                    ch = System.`in`.read().toChar()
                    println(ch)
                    if (ch == 'q' || ch == 'Q') {
                        isRunning = false
                        println("Exit input case")
                    }
                }
            })
        }

        private fun prepareObject(): GameObject {
            val circlePattern: Set<Pair<Int, Int>> =
                setOf(Pair(0, 0), Pair(1, 0), Pair(2, 0), Pair(2, 1), Pair(2, 2), Pair(1, 2), Pair(0, 2), Pair(0, 1))
            val gameObject = GameObject(null)

            var position = PositionProperty(gameObject, Coord(15, 10))
            var drawProperty = DrawProperty(gameObject, circlePattern)
            drawProperty.bgColor = TextColor.ANSI.BLUE

            var moveProperty: MoveProperty = MoveProperty(gameObject, MovementDirection.Down)
            moveProperty.speed = 1

            gameObject.addProperty("drawable", drawProperty)
            gameObject.addProperty("position", position)
            gameObject.addProperty("move", moveProperty)
            return gameObject
        }

        private suspend fun runSys() {
            val drawSystem: DefaultDrawSystem = GlobalContext.get().get()
            val moveSystem: MoveSystem = GlobalContext.get().get()
            // launch a new coroutine and continue
            while (true) {
                delay(16L) // non-blocking delay for 1 second (default time unit is ms)
                moveSystem.run()
                drawSystem.run()
            }

        }
    }
}