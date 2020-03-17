package sorting.algorithm

import sorting.common.AbstractSortingAlgorithm
import sorting.common.AlgorithmComplexity
import sorting.common.SortingResult

object CocktailShakerSort : AbstractSortingAlgorithm(
    name = "Cocktail shaker sort",
    complexity = object : AlgorithmComplexity {
        override val explain = "1 + 2 + ... + (n - 1) = (n^2)/2 - n/2"

        override fun expectedComparisons(size: Int): Long {
            val n = size.toDouble()
            val result = (n * n) / 2 - n / 2
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
        var firstIndex = 0
        var lastIndex = source.lastIndex
        do {
            for (i in firstIndex until lastIndex) {
                comparisons++
                if (source[i] > source[i + 1]) {
                    permutations++
                    val tmp = source[i]
                    source[i] = source[i + 1]
                    source[i + 1] = tmp
                }
            }
            lastIndex--
            for (i in lastIndex downTo firstIndex + 1) {
                comparisons++
                if (source[i] < source[i - 1]) {
                    permutations++
                    val tmp = source[i]
                    source[i] = source[i - 1]
                    source[i - 1] = tmp
                }
            }
            firstIndex++
        } while (firstIndex < lastIndex)
        return SortingResult(
            values = source.toList(),
            timeInMillis = System.currentTimeMillis() - timeStart,
            comparisons = comparisons,
            permutations = permutations
        )
    }
}
