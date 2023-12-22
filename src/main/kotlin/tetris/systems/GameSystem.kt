package tetris.systems

import tetris.component.GameProperty

interface GameSystem {
    fun addComponent(component: GameProperty)
    fun run()
}