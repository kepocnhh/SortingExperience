package sorting.algorithm

import sorting.common.AbstractSortingAlgorithm
import sorting.common.AlgorithmComplexity
import sorting.common.SortingResult

object StupidSort : AbstractSortingAlgorithm(
    name = "Stupid sort",
    complexity = object: AlgorithmComplexity {
        override val explain = "((n^2 - n)/4) * (1 + (2*n - 1)/3) + n - 1"

        override fun expectedComparisons(size: Int): Long {
            val n = size.toDouble()
            val result = ((n * n - n)/4) * (1 + (2*n - 1)/3) + n - 1
            return result.toLong()
        }

        override fun toString(): String {
            return "O(n^3)"
        }
    }
) {
    override fun <T : Comparable<T>> sort(source: Array<T>): SortingResult<T> {
        val timeStart = System.currentTimeMillis()
        var comparisons = 0L
        var permutations = 0L
        var i = 0
        while (i < source.size - 1) {
            comparisons++
            if(source[i] > source[i+1]) {
                permutations++
                val tmp = source[i]
                source[i] = source[i+1]
                source[i+1] = tmp
                i = 0
            } else i++
        }
        return SortingResult(
            values = source.toList(),
            timeInMillis = System.currentTimeMillis() - timeStart,
            comparisons = comparisons,
            permutations = permutations
        )
    }
}
