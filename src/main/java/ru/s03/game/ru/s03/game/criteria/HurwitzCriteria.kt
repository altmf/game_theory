package ru.s03.game.ru.s03.game.criteria

import ru.s03.game.GameMatrix
import ru.s03.game.GameVector
import ru.s03.game.ICriteria

class HurwitzCriteria(gameMatrix: GameMatrix, optimisticKoef: Double) : ICriteria {

    val gameMatrix: GameMatrix
    val optimisticKoef: Double

    init {
        this.gameMatrix = gameMatrix
        this.optimisticKoef = optimisticKoef
    }

    inner class HurwitzParam(xmax: Double, xmin: Double, optXmax: Double){
        val xmax: Double
        val xmin: Double
        val optXmax: Double

        val value: Double

        init{
            this.xmax = xmax
            this.xmin = xmin
            this.optXmax = optXmax
            value = xmax * optXmax + xmin * (1 - optXmax)
        }

    }

    fun GameMatrix.getHurwitzParams(): List<Pair<GameVector, HurwitzParam>> {
        return this.alternatives.map { a -> Pair(a, HurwitzParam(a.max()!!, a.min()!!, optimisticKoef)) }
    }

    override fun optimum(): List<GameVector> {
        val hpar = gameMatrix.getHurwitzParams()
        val maxHurw = hpar.maxWith(Comparator { o1, o2 -> o1.second.value.compareTo(o2.second.value) })
        return hpar
                .filter { r -> r.second == maxHurw!!.second }
                .map { m -> m.first }
    }
}