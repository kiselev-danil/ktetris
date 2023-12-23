package tetris.systems.command

class MacroCommand: ICommand {
    private var queue: MutableList<ICommand> = ArrayList<ICommand>()
    private var executed: MutableList<ICommand> = ArrayList<ICommand>()

    fun push(cmd: ICommand) {
        queue.add(queue.lastIndex + 1, cmd)
    }

    override suspend fun execute() {
        if (queue.size > 0) {
            queue.first().execute()
            executed.add(executed.lastIndex + 1, queue.first())
            queue.removeAt(0)
        }
    }

    override suspend fun undo() {
        if (executed.size > 0) {
            executed.last().undo()
            queue.add(0,executed.last())
            executed.removeAt(executed.lastIndex)
        }
    }
}