package di

import com.googlecode.lanterna.TextColor
import com.googlecode.lanterna.terminal.DefaultTerminalFactory
import com.googlecode.lanterna.terminal.Terminal
import org.koin.dsl.module
import tetris.systems.DefaultDrawSystem
import tetris.systems.MoveSystem
import tetris.command.CommandQueue

val commonDIModule = module {
    single<Terminal> { DefaultTerminalFactory().createTerminal() }
    single<TextColor> { TextColor.ANSI.DEFAULT }
    single<DefaultDrawSystem> { DefaultDrawSystem() }
    single<MoveSystem> { MoveSystem() }
    single<CommandQueue> { CommandQueue() }
}