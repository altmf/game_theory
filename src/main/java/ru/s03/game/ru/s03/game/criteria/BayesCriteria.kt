package ru.s03.game.ru.s03.game.criteria

import ru.s03.game.GameMatrix
import ru.s03.game.GameVector
import ru.s03.game.ICriteria
import ru.s03.game.ProbabilityGameMatrix

class BayesCriteria(gameMatrix: ProbabilityGameMatrix) : ICriteria {

    val gameMatrix: ProbabilityGameMatrix

    init {
        this.gameMatrix = gameMatrix
    }

    fun ProbabilityGameMatrix.bayesProbability(): List<Pair<GameVector, Double?>> {
        var am: MutableList<Pair<GameVector, Double>> = mutableListOf()
        for (a in this.alternatives) {
            var alprob: Double = 0.0
            for (i in 0..a.values.lastIndex) {
                alprob += a.values[i] * this.probabilities[i]
            }
            am.add(Pair(a, alprob))
        }
        return am.toList();
    }

    override fun optimum(): List<GameVector> {
        val risk = gameMatrix.bayesProbability()
        val maxBayes = risk.maxWith(Comparator { o1, o2 -> o1.second!!.compareTo(o2.second!!) })
        return risk
                .filter { r -> r.second == maxBayes!!.second }
                .map { m -> m.first }
    }
}