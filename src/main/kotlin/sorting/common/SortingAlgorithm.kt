package sorting.common

interface SortingAlgorithm {
    val name: String
    val complexity: AlgorithmComplexity
    fun <T: Comparable<T>> sort(source: Iterable<T>): SortingResult<T>
}

data class SortingResult<T: Comparable<T>>(
    val values: List<T>,
    val timeInMillis: Long,
    val comparisons: Long,
    val permutations: Long
)

abstract class AbstractSortingAlgorithm(
    override val name: String,
    override val complexity: AlgorithmComplexity
): SortingAlgorithm {
    protected abstract fun <T : Comparable<T>> sort(source: Array<T>): SortingResult<T>
    private fun <T : Comparable<T>> sort(source: Collection<T>): SortingResult<T> {
        return if (source.size < 2) {
            val timeStart = System.currentTimeMillis()
            SortingResult(
                values = source.toList(),
                timeInMillis = System.currentTimeMillis() - timeStart,
                comparisons = 0,
                permutations = 0
            )
        }
        else sort(source.toTypedArray<Comparable<T>>() as Array<T>)
    }
    override fun <T : Comparable<T>> sort(source: Iterable<T>): SortingResult<T> {
        return if (source is Collection) sort(source)
        else sort(source.toMutableList())
    }
}

interface AlgorithmComplexity {
    val explain: String
    fun expectedComparisons(size: Int): Long
}
