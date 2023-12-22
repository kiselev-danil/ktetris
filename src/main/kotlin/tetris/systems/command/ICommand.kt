package tetris.systems.command

interface ICommand {
    suspend fun execute()
    suspend fun undo()
}