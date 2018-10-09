package ru.s03.game.ru.s03.game.analit

import ru.s03.game.GameMatrix
import org.apache.commons.math3.optim.PointValuePair
import org.apache.commons.math3.optim.linear.*
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType
import java.util.ArrayList

open class Solver (gameMatrix: GameMatrix) {

    val gameMatrix: GameMatrix

    init{
        this.gameMatrix = gameMatrix
    }

    fun solve(): Solve{
        val goalf: List<Double> = gameMatrix.alternatives.map { a -> 1.0 }
        val f = LinearObjectiveFunction(goalf.toDoubleArray(), 0.0)

        val constraints = ArrayList<LinearConstraint>()
        for (alt in gameMatrix.alternatives){
            constraints.add(LinearConstraint(alt.values.toDoubleArray(), Relationship.LEQ,  1.0))

        }

        val solution = SimplexSolver().optimize(f, LinearConstraintSet(constraints), GoalType.MAXIMIZE,
                NonNegativeConstraint(true))

        val sl: List<Double> = solution.getPoint().toList()
        val solve = Solve(solution.getValue(), sl, gameMatrix.alternativeNames)

        return solve
    }
}