//package ru.s03.game.test;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.junit.jupiter.api.Test;
import ru.s03.game.GameMatrix;

import java.util.ArrayList;
import java.util.Collection;

public class SolverTestJava {
    @Test
    public void solve(){

        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { 1, 1}, 0);

        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
        constraints.add(new LinearConstraint(new double[] { 2.0, 4.0}, Relationship.LEQ, 1));
        constraints.add(new LinearConstraint(new double[] { 6.0, 2.0}, Relationship.LEQ, 1));

        //create and run solver
        PointValuePair solution = null;
        solution = new SimplexSolver().optimize(f, new LinearConstraintSet(constraints), GoalType.MAXIMIZE,
                new NonNegativeConstraint(true));

        if (solution != null) {
            //get solution
            double max = solution.getValue();
            System.out.println("Цена игры: " + max);

            //print decision variables
            for (int i = 0; i < solution.getPoint().length; i++) {
                System.out.print(solution.getPoint()[i] + ";\t");
            }
        }
    }

    @Test
    public void solve2(){

        LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { 1, 1}, 0);

        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
        constraints.add(new LinearConstraint(new double[] { 2.0, 6.0}, Relationship.GEQ, 1));
        constraints.add(new LinearConstraint(new double[] { 4.0, 2.0}, Relationship.GEQ, 1));

        //create and run solver
        PointValuePair solution = null;
        solution = new SimplexSolver().optimize(f, new LinearConstraintSet(constraints), GoalType.MINIMIZE,
                new NonNegativeConstraint(true));

        if (solution != null) {
            //get solution
            double max = solution.getValue();
            System.out.println("Цена игры: " + max);

            //print decision variables
            for (int i = 0; i < solution.getPoint().length; i++) {
                System.out.print(solution.getPoint()[i] + ";\t");
            }
        }
    }
}
