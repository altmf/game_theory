package ru.s03.game.ru.s03.game.analit

import ru.s03.game.GameMatrix
import java.text.DecimalFormat
import java.text.NumberFormat

class Solve(gamePriceObr: Double, solutions: List<Double>, names: List<String>) {

    val gamePrice: Double
    val gamePriceObr: Double
    val solutions: List<Double>
    val names: List<String>

    private val formatter: NumberFormat = DecimalFormat("#0.00")

    init{
        this.gamePrice =  1 / gamePriceObr
        this.gamePriceObr = gamePriceObr;
        this.solutions = solutions
        this.names = names
    }

    override fun toString(): String{
        var s =  "Цена игры: " + formatter.format(gamePrice) + "\n"
        for (i in 0..solutions.lastIndex){
            s += "$i) " + names[i] + " c вероятностью = " + formatter.format(solutions[i] / gamePriceObr) + "\n"
        }
        return s
    }

    fun itersect(matrix: GameMatrix): String{
        var s =  "Цена игры: " + formatter.format(gamePrice) + "\n"
        for (j in 0..matrix.alternativeNames.lastIndex) {
            var f = false
            val a = matrix.alternativeNames[j]
            for (i in 0..solutions.lastIndex) {
                if (a.equals(names[i])) {
                    s += "$j) " + names[i] + " c вероятностью = " + formatter.format(solutions[i] / gamePriceObr) + "\n"
                    f = true
                    break
                }
            }
            if (!f){
                s += "$j) " + a + " c вероятностью = 0\n"
            }
        }
        return s
    }
}