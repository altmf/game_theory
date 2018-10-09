package ru.s03.game

import java.text.DecimalFormat
import java.text.NumberFormat



open class GameVector(name: String, values: List<Double>, key: Int = -1) : Comparable<GameVector> {

    val name: String
    val values: List<Double>
    val key: Int

    private val formatter:NumberFormat = DecimalFormat("#0.00")

    init {
        this.name = name;
        this.values = values;
        this.key = key;
    }

    public fun max(): Double? {
        return values.max();
    }

    public fun min(): Double? {
        return values.min();
    }

    override fun toString(): String{
        return name + ": " + values
                .map { v -> formatter.format(v) }
                .reduce( {f1: String, f2: String -> "$f1   $f2"})
    }

    override fun compareTo(other: GameVector): Int {
        var compare = 0
        if (this.key == other.key){
            return compare
        }
        var great = true
        for (i in 0..this.values.lastIndex){
            great = great && this.values[i] >= other.values[i]
        }
        if (great){
            compare = 1
        }else{
            var less = true
            for (i in 0..this.values.lastIndex){
                less = less && this.values[i] <= other.values[i]
            }
            if (less){
                compare = -1
            }
        }
        return compare
    }
}