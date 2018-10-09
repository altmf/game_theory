package ru.s03.game

open class GameMatrix(matrix: List<List<Double>>, alternativeNames: List<String>, natureStateNames: List<String>) {
    val matrix: List<List<Double>>
    val alternativeNames: List<String>
    val natureStateNames: List<String>

    val alternatives: List<GameVector>
    val natureStates: List<GameVector>

    init {
        this.matrix = matrix;
        this.alternativeNames = alternativeNames
        this.natureStateNames = natureStateNames

        var alts: MutableList<GameVector> = mutableListOf()
        for (i in 0..matrix.lastIndex) {
            val currAlternative = alternativeNames[i]
            val gameVector = GameVector(currAlternative, matrix[i], i)
            alts.add(gameVector)
        }
        alternatives = alts.toList()

        var nss: MutableList<GameVector> = mutableListOf()
        val lastIndex = matrix[0].lastIndex // нет провеврки на равенство длин всех строк, считаем что они равны
        for (j in 0..lastIndex) {
            val currState = natureStateNames[j]
            var states: MutableList<Double> = mutableListOf()
            for (i in 0..matrix.lastIndex) {
                states.add(matrix[i][j])
            }
            val gameVector = GameVector(currState, states.toList(), j)
            nss.add(gameVector)
        }
        natureStates = nss.toList()
    }

//    fun set(i : Int, j : Int, value : Double){
//        val ma = alternatives.get(i).values.toMutableList()
//        ma.set(j, value)
//        var mal = alternatives.toMutableList()
//        var gv = GameVector(alternatives.get(i).name, ma.toList(),  alternatives.get(i).key)
//        mal.set(i, gv)
//        alternatives = mal.toList()
//    }

    open fun change (i : Int, j : Int, value : Double) : GameMatrix{
        var mml = this.matrix.toMutableList()

        var rowi = mml[i].toMutableList()
        rowi.set(j, value)

        mml.set(i, rowi)

        return GameMatrix(mml.toList(), alternativeNames, natureStateNames)
    }

    open fun changeAlternativeName (i : Int, value : String) : GameMatrix{
        var list = alternativeNames.toMutableList()
        list.set(i, value)

        return GameMatrix(matrix, list.toList(), natureStateNames)
    }

    open fun changeNatureStateName (j : Int, value : String) : GameMatrix{
        var list = natureStateNames.toMutableList()
        list.set(j, value)

        return GameMatrix(matrix, alternativeNames, list.toList())
    }

    fun size() : Pair<Int, Int>{
        return Pair(alternatives.size, natureStates.size)
    }

    override fun toString(): String {
        return "Состояния природы:\n" +
            natureStateNames.reduce { n1: String, n2: String -> "$n1;\n$n2" } +
            "\nМатрица игры:\n" +
            alternatives
                .map { a: GameVector -> a.toString() }
                .reduce { a1: String, a2: String -> "$a1;\n$a2" }
    }

    protected fun dominateSet(gvl: List<GameVector>, list: MutableList<String>, dvv: Int) : MutableSet<GameVector>{
        var dSet: MutableSet<GameVector> = mutableSetOf()
        for (gv in gvl){
            for (gvv in gvl){
                if (!dSet.contains(gv) && !dSet.contains(gvv)) {
                    if (gv.compareTo(gvv) == dvv) {
                        dSet.add(gv)
                        list.add("[$gvv] доминирует [$gv]")
                    }
                }
            }
        }
        return dSet
    }

    open fun newMatrix(dCol: MutableSet<GameVector>, dRow: MutableSet<GameVector>)
                            : GameMatrix{
        var result: MutableList<MutableList<Double>> = mutableListOf()
        var ralternativeNames: MutableList<String> = mutableListOf()
        var rnatureStateNames: MutableList<String> = mutableListOf()

        val dIndex =  dCol.map { c -> c.key }.toList()

        for (i in 0 .. natureStateNames.lastIndex){
            if (!dIndex.contains(i)){
                rnatureStateNames.add(natureStateNames[i])
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
        return GameMatrix(rlist, ralternativeNames.toList(), rnatureStateNames.toList())
    }

    fun dominateMatrix(): Pair<GameMatrix, List<String>>{
        var list: MutableList<String> = mutableListOf()

        var dCol: MutableSet<GameVector> = dominateSet(this.natureStates, list, 1)
        var dRow: MutableSet<GameVector> = dominateSet(this.alternatives, list, -1)

        val newMatrix = newMatrix(dCol, dRow)

        var ddgm = Pair(newMatrix, list.toList())

        val ret = iterate(ddgm, list)
        return ret;
    }

    protected fun iterate(ddgm: Pair<GameMatrix, List<String>>, list: MutableList<String>)
            : Pair<GameMatrix, List<String>>{
        var dgm = this
        var lddgm = ddgm

        while (dgm.size() != lddgm.first.size()){
            dgm = lddgm.first
            list.addAll(lddgm.second)
            lddgm = dgm.dominateMatrix()
        }

        return Pair(dgm,list.toList().distinct())
    }


    fun minClearPrice(): Double{
        val map: List<Double> = this.alternatives.map { a -> a?.min() ?: 0.0 }
        return map?.max() ?: 0.0
    }

    fun maxClearPrice(): Double{
        val map: List<Double> = this.natureStates.map { a -> a?.max() ?: 0.0 }
        return map?.min() ?: 0.0
    }

    fun existsClearStrategy() : Boolean{
        return minClearPrice() >= maxClearPrice()
    }
}