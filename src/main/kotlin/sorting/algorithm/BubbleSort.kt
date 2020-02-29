package sorting.algorithm

import sorting.common.AbstractSortingAlgorithm
import sorting.common.AlgorithmComplexity
import sorting.common.SortingResult

object BubbleSort : AbstractSortingAlgorithm(
    name = "Bubble sort",
    complexity = object: AlgorithmComplexity {
        override val explain = "1 + 2 + ... + (n - 1) = (n^2)/2 - n/2"

        override fun expectedComparisons(size: Int): Long {
            val n = size.toDouble()
            val result = (n*n)/2 - n/2
            return result.toLong()
        }

        override fun toString(): String {
            return "O(n^2)"
        }
    }
) {
    override fun <T : Comparable<T>> sort(source: Array<T>): SortingResult<T> {
        val timeStart = System.currentTimeMillis()
        var comparisons = 0L
        var permutations = 0L
        var find = false
        for (last in source.indices) {
            for (i in 0 until source.size - 1 - last) {
                comparisons++
                if(source[i] > source[i+1]) {
                    find = true
                    permutations++
                    val tmp = source[i]
                    source[i] = source[i+1]
                    source[i+1] = tmp
                }
            }
            if(!find) break
        }
        return SortingResult(
            values = source.toList(),
            timeInMillis = System.currentTimeMillis() - timeStart,
            comparisons = comparisons,
            permutations = permutations
        )
    }
}
