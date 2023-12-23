package tetris.command

interface ICommand {
    suspend fun execute()
    suspend fun undo()
}