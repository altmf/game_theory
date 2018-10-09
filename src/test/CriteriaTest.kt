package ru.s03.game.test


import org.junit.jupiter.api.Test
import ru.s03.game.GameMatrix
import ru.s03.game.ICriteria
import ru.s03.game.ProbabilityGameMatrix
import ru.s03.game.ru.s03.game.criteria.*

internal class CriteriaTest {

    private fun matrix(): GameMatrix {
        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое")
        val matrix: List<List<Double>> = listOf(
                listOf(0.0, 2.0, 5.0),
                listOf(2.0, 3.0, 1.0),
                listOf(4.0, 3.0, -1.0)
        )
        val gm = GameMatrix(matrix, alternativeNames, natureStateNames)
        return gm;
    }

    private fun probabilityMatrix(): ProbabilityGameMatrix {
        val alternativeNames: List<String> = listOf("Культура 1", "Культура 2", "Культура 3")
        val natureStateNames: List<String> = listOf("Прохладное и дождливое", "Нормальное", "Жаркое и сухое")
        val matrix: List<List<Double>> = listOf(
                listOf(0.0, 2.0, 5.0),
                listOf(2.0, 3.0, 1.0),
                listOf(4.0, 3.0, -1.0)
        )
        val probabilities: List<Double> = listOf(0.2, 0.5, 0.3)
        val gm = ProbabilityGameMatrix(matrix, alternativeNames, natureStateNames, probabilities)
        return gm;
    }

    private fun testCriteria(gameMatrix: GameMatrix, criteria: ICriteria, name: String){
        println(gameMatrix.toString())
        val optimum = criteria.optimum()
        println("$name. Оптимальная стратегия: ")
        optimum.forEach { o -> println(o.toString()) }
    }

    @Test
    fun testWaldCriteria() {
        val matrix = matrix();
        val criteria = WaldCriteria(matrix)
        testCriteria(matrix, criteria, "Критерий Вальда")
    }

    @Test
    fun testOptimismCriteria(){
        val matrix = matrix();
        val criteria = OptimismCriteria(matrix)
        testCriteria(matrix, criteria, "Критерий Оптимизма")
    }

    @Test
    fun testPessimismCriteria(){
        val matrix = matrix();
        val criteria = PessimismCriteria(matrix)
        testCriteria(matrix, criteria, "Критерий Пессимизма")
    }

    @Test
    fun testSavageCriteria(){
        val matrix = matrix();
        val criteria = SavageCriteria(matrix)
        testCriteria(matrix, criteria, "Критерий Сэвиджа")
    }

    @Test
    fun testHurwitzCriteria(){
        val matrix = matrix();
        val opt = 0.6
        val criteria = HurwitzCriteria(matrix, opt)
        testCriteria(matrix, criteria, "Критерий Гурвица")
    }

    @Test
    fun testBayesCriteria(){
        val matrix = probabilityMatrix();
        val criteria = BayesCriteria(matrix)
        testCriteria(matrix, criteria, "Критерий Байеса")
    }

    @Test
    fun testLaplaceCriteria(){
        val matrix = matrix();
        val criteria = LaplaceCriteria(matrix)
        testCriteria(matrix, criteria, "Критерий Лапласа")
    }

    @Test
    fun testGermeierCriteria(){
        val matrix = probabilityMatrix();
        val criteria = GermeierCriteria(matrix)
        testCriteria(matrix, criteria, "Критерий Гермейера")
    }
}