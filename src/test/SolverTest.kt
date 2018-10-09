package ru.s03.game.test


import org.junit.jupiter.api.Test
import ru.s03.game.GameMatrix
import ru.s03.game.ProbabilityGameMatrix
import ru.s03.game.ru.s03.game.analit.Solver

internal class SolverTest {

    @Test
    fun solveGameMatrixTest() {

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
        println()
        val s: Solver = Solver(dgm)
        val solve = s.solve()
        println(solve)
    }

    @Test
    fun solveGameMatrixTest5() {

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
        println()
        val s: Solver = Solver(dgm)
        val solve = s.solve()
        println(solve)
        println(solve.itersect(gm))
    }

    @Test
    fun solveGameMatrixTest2() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое", "Ветреное")
        val matrix: List<List<Double>> = listOf(
                listOf(2.0, 4.0, 8.0, 5.0),
                listOf(6.0, 2.0, 4.0, 6.0),
                listOf(3.0, 2.0, 5.0, 4.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        println(gm.toString())
        println()
        val s: Solver = Solver(gm)
        val solve = s.solve()
        println(solve)
    }

    @Test
    fun solveGameMatrixTest3() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное")
        val matrix: List<List<Double>> = listOf(
                listOf(3.0, 8.0),
                listOf(7.0, 4.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        println(gm.toString())
        println()
        val s: Solver = Solver(gm)
        val solve = s.solve()
        println(solve)
    }

    @Test
    fun solveGameMatrixTest4() {

        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое")
        val matrix: List<List<Double>> = listOf(
                listOf(0.0, 2.0, 5.0),
                listOf(2.0, 3.0, 1.0),
                listOf(4.0, 3.0, -1.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        println(gm.toString())
        println()
        val s: Solver = Solver(gm)
        val solve = s.solve()
        println(solve)
    }

//    @Test
//    fun dominateProbabilityGameMatrixTest() {
//
//        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
//        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое", "Ветренное")
//        val matrix: List<List<Double>> = listOf(
//                listOf(2.0, 4.0, 8.0, 5.0),
//                listOf(6.0, 2.0, 4.0, 6.0),
//                listOf(3.0, 2.0, 5.0, 4.0)
//        )
//        val probabilities: List<Double> = listOf(0.3, 0.4, 0.2, 0.1)
//        val gm = ProbabilityGameMatrix(matrix, alternativeNames, natureStateNames, probabilities)
//        val (dgm, list) = gm.dominateMatrix()
//        println(dgm.toString())
//        println(list.reduce({s1, s2 -> s1 + "\n" + s2}))
//    }
}