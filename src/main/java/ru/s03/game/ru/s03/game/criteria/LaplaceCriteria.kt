package ru.s03.game.ru.s03.game.criteria

import ru.s03.game.GameMatrix
import ru.s03.game.GameVector
import ru.s03.game.ICriteria
import ru.s03.game.ProbabilityGameMatrix

class LaplaceCriteria(gameMatrix: GameMatrix) : ICriteria {

    val gameMatrix: GameMatrix

    init {
        this.gameMatrix = gameMatrix
    }

    fun GameMatrix.arithemicMean(): List<Pair<GameVector, Double>> {
        return this.alternatives.map { m -> Pair(m, m.values.average()) }
    }

    override fun optimum(): List<GameVector> {
        val risk = gameMatrix.arithemicMean()
        val maxBayes = risk.maxWith(Comparator { o1, o2 -> o1.second.compareTo(o2.second) })
        return risk
                .filter { r -> r.second == maxBayes!!.second }
                .map { m -> m.first }
    }
}