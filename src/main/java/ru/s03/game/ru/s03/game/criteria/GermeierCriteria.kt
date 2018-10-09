package ru.s03.game.ru.s03.game.criteria

import ru.s03.game.GameMatrix
import ru.s03.game.GameVector
import ru.s03.game.ICriteria
import ru.s03.game.ProbabilityGameMatrix

class GermeierCriteria(gameMatrix: ProbabilityGameMatrix) : ICriteria {

    val gameMatrix: ProbabilityGameMatrix

    init {
        this.gameMatrix = gameMatrix
    }

    fun ProbabilityGameMatrix.germeierMetric(): List<Pair<GameVector, Double?>> {
        var am: MutableList<Pair<GameVector, Double?>> = mutableListOf()
        for (a in this.alternatives) {
            var alprob: MutableList<Double> = mutableListOf()
            for (i in 0..a.values.lastIndex) {
                val ns: GameVector? = this.natureStates.find { n -> n.key == i }
                val average = (ns!!.values.sum() - a.values[i]) / (ns!!.values.count() - 1)
                val g = -average
                val gp = g *this.probabilities[i]
                alprob.add(gp)
            }
            am.add(Pair(a, alprob.min()))
        }
        return am.toList();
    }

    override fun optimum(): List<GameVector> {
        val v = gameMatrix.germeierMetric()
        val max = v.maxWith(Comparator { o1, o2 -> o1.second!!.compareTo(o2.second!!) })
        return v
                .filter { r -> r.second == max!!.second }
                .map { m -> m.first }
    }
}