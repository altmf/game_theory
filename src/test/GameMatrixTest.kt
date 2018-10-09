package ru.s03.game.test


import org.junit.jupiter.api.Test
import ru.s03.game.GameMatrix
import ru.s03.game.ProbabilityGameMatrix

internal class GameMatrixTest {

    @Test
    fun toStringGameMatrixTest() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое")
        val matrix: List<List<Double>> = listOf(
                listOf(0.0, 2.0, 5.0),
                listOf(2.0, 3.0, 1.0),
                listOf(4.3, 2.0, -1.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        println(gm.toString())
    }

    @Test
    fun toStringProbabilityGameMatrixTest() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое")
        val matrix: List<List<Double>> = listOf(
                listOf(0.0, 2.0, 5.0),
                listOf(2.0, 3.0, 1.0),
                listOf(4.3, 2.0, -1.0)
        )
        val probabilities: List<Double> = listOf(0.3, 0.5, 0.2)
        val gm = ProbabilityGameMatrix(matrix, alternativeNames, natureStateNames, probabilities)
        println(gm.toString())
    }

    @Test
    fun toStringGameMatrixTest2() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое", "Ветренное")
        val matrix: List<List<Double>> = listOf(
                listOf(2.0, 4.0, 8.0, 5.0),
                listOf(6.0, 2.0, 4.0, 6.0),
                listOf(3.0, 2.0, 5.0, 4.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        println(gm.toString())
    }

    @Test
    fun dominateGameMatrixTest() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое", "Ветренное")
        val matrix: List<List<Double>> = listOf(
                listOf(2.0, 4.0, 8.0, 5.0),
                listOf(6.0, 2.0, 4.0, 6.0),
                listOf(3.0, 2.0, 5.0, 4.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        val (dgm, list) = gm.dominateMatrix()
        println(dgm.toString())
        println(list.reduce({s1, s2 -> s1 + "\n" + s2}))
    }

    @Test
    fun dominateProbabilityGameMatrixTest() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое", "Ветренное")
        val matrix: List<List<Double>> = listOf(
                listOf(2.0, 4.0, 8.0, 5.0),
                listOf(6.0, 2.0, 4.0, 6.0),
                listOf(3.0, 2.0, 5.0, 4.0)
        )
        val probabilities: List<Double> = listOf(0.3, 0.4, 0.2, 0.1)
        val gm = ProbabilityGameMatrix(matrix, alternativeNames, natureStateNames, probabilities)
        val (dgm, list) = gm.dominateMatrix()
        println(dgm.toString())
        println(list.reduce({s1, s2 -> s1 + "\n" + s2}))
    }


    @Test
    fun chageGameMatrixTest() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое", "Ветренное")
        val matrix: List<List<Double>> = listOf(
                listOf(1.0, 1.0, 1.0, 1.0),
                listOf(1.0, 1.0, 1.0, 1.0),
                listOf(1.0, 1.0, 1.0, 1.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        val cgm = gm.change(1, 2, 2.0)
        println(cgm.toString())
        //println(list.reduce({s1, s2 -> s1 + "\n" + s2}))
    }

    @Test
    fun chageNamesGameMatrixTest() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое", "Ветренное")
        val matrix: List<List<Double>> = listOf(
                listOf(1.0, 1.0, 1.0, 1.0),
                listOf(1.0, 1.0, 1.0, 1.0),
                listOf(1.0, 1.0, 1.0, 1.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        val cgm = gm.changeAlternativeName(1, "Kul")
        println(cgm.toString())

        val cgm1 = gm.changeNatureStateName(1, "Kul")
        println(cgm1.toString())
        //println(list.reduce({s1, s2 -> s1 + "\n" + s2}))
    }
}