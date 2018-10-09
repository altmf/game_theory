package ru.s03.game.ru.s03.game.criteria

import ru.s03.game.GameMatrix
import ru.s03.game.GameVector
import ru.s03.game.ICriteria

class PessimismCriteria(gameMatrix : GameMatrix) : ICriteria {

    val gameMatrix: GameMatrix

    init{
        this.gameMatrix = gameMatrix
    }

    override fun optimum(): List<GameVector> {
        val mins = gameMatrix.alternatives.map { a -> Pair(a, a.min()) }
        val min =  mins.minWith( Comparator { o1, o2 ->  o1.second!!.compareTo(o2.second!!)})
        return mins
                .filter { m -> m.second == min!!.second }
                .map { m -> m.first }
    }
}