package ru.s03.game.test


import org.junit.jupiter.api.Test
import ru.s03.game.GameVector

internal class GameVectorTest {

    @Test
    public fun toStringTest() {
        val gv: GameVector = GameVector("Test", listOf(1.2, 1.3, 1.4))
        println(gv.toString())
    }
}