package ru.s03.game.ru.s03.game.criteria

import ru.s03.game.GameMatrix
import ru.s03.game.GameVector
import ru.s03.game.ICriteria

class SavageCriteria(gameMatrix: GameMatrix) : ICriteria {

    val gameMatrix: GameMatrix

    init {
        this.gameMatrix = gameMatrix
    }

    fun GameMatrix.risk(): List<Pair<GameVector, Double?>> {
        val maxStates = this.natureStates.map { n -> Pair(n, n.values.max()) }
                .map { n -> n.first.key to n.second }.toMap()

        var am: MutableList<Pair<GameVector, List<Double>>> = mutableListOf()
        for (a in this.alternatives) {
            var v: MutableList<Double> = mutableListOf()
            for (i in 0..a.values.lastIndex) {
                val mn = maxStates.get(i)
                v.add(mn!! - a.values[i])
            }
            am.add(Pair(a, v.toList()))
        }
        return am.map { m -> Pair(m.first, m.second.max()) }
    }

    override fun optimum(): List<GameVector> {
        val risk = gameMatrix.risk()
        val minRisk = risk.minWith(Comparator { o1, o2 -> o1.second!!.compareTo(o2.second!!) })
        return risk
                .filter { r -> r.second == minRisk!!.second }
                .map { m -> m.first }
    }
}