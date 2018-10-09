package ru.s03.game

import java.text.DecimalFormat
import java.text.NumberFormat

open class ProbabilityGameMatrix(matrix: List<List<Double>>, alternativeNames: List<String>,
                                 natureStateNames: List<String>, probabilities: List<Double>) :
        GameMatrix(matrix, alternativeNames, natureStateNames) {
    val probabilities: List<Double>

    init {
        this.probabilities = probabilities;
    }

    override fun change (i : Int, j : Int, value : Double) : GameMatrix{
        val cm = super.change(i, j, value)
        return ProbabilityGameMatrix(cm.matrix, cm.alternativeNames, cm.natureStateNames, probabilities)
    }

    override fun changeAlternativeName (i : Int, value : String) : GameMatrix{
        val cm = super.changeAlternativeName(i, value)
        return ProbabilityGameMatrix(cm.matrix, cm.alternativeNames, cm.natureStateNames, probabilities)
    }

    override fun changeNatureStateName (j : Int, value : String) : GameMatrix{
        val cm = super.changeNatureStateName(j, value)
        return ProbabilityGameMatrix(cm.matrix, cm.alternativeNames, cm.natureStateNames, probabilities)
    }

    fun changeProbability (j : Int, value : Double) : GameMatrix{
        var list = probabilities.toMutableList()
        list.set(j, value)

        return ProbabilityGameMatrix(matrix, alternativeNames, natureStateNames, list.toList())
    }

    override fun toString(): String {
        var s = ""
        val formatter: NumberFormat = DecimalFormat("#0.00")
        for (i in 0 .. natureStateNames.lastIndex){
            s += natureStateNames[i] + " = " + formatter.format(probabilities[i]) + "\n"
        }
        return "Состояния природы:\n" +
                s +
                "Матрица игры:\n" +
                alternatives
                        .map { a: GameVector -> a.toString() }
                        .reduce { a1: String, a2: String -> "$a1;\n$a2" }
    }

    override fun newMatrix(dCol: MutableSet<GameVector>, dRow: MutableSet<GameVector>)
            : GameMatrix{
        var result: MutableList<MutableList<Double>> = mutableListOf()
        var ralternativeNames: MutableList<String> = mutableListOf()
        var rnatureStateNames: MutableList<String> = mutableListOf()
        var rprobailities: MutableList<Double> = mutableListOf()

        val dIndex =  dCol.map { c -> c.key }.toList()

        for (i in 0 .. natureStateNames.lastIndex){
            if (!dIndex.contains(i)){
                rnatureStateNames.add(natureStateNames[i])
            }
        }

        for (i in 0 .. probabilities.lastIndex){
            if (!dIndex.contains(i)){
                rprobailities.add(probabilities[i])
            }
        }

        for (gv in this.alternatives){
            if (!dRow.contains(gv)){
                var nr: MutableList<Double> = mutableListOf()
                for (i in 0 .. gv.values.lastIndex){
                    if (!dIndex.contains(i)){
                        nr.add(gv.values[i])
                    }
                }
                result.add(nr)
                ralternativeNames.add(gv.name)
            }
        }

        val rlist = result.map { r -> r.toList() }.toList()
        return ProbabilityGameMatrix(rlist, ralternativeNames.toList(), rnatureStateNames.toList(),
                rprobailities.toList())
    }

}