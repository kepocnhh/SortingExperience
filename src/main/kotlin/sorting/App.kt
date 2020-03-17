package sorting

import sorting.algorithm.BubbleExSort
import sorting.algorithm.BubbleSort
import sorting.algorithm.CocktailShakerSort
import sorting.algorithm.StupidSort
import sorting.common.SortingAlgorithm
import java.util.Random
import kotlin.math.pow

enum class SourceType {
    RANDOM,
    SORTED,
    SORTED_RIGHT,
    REVERSED,
    EQUALS
}

fun generateSource(size: Int): List<String> {
    val random = Random()
    return (0 until size).map {
        (0..4).map {
            (random.nextInt(25) + 97).toChar()
        }.joinToString("")
    }
}
fun generateSource(size: Int, sourceType: SourceType): List<String> {
    return when(sourceType) {
        SourceType.RANDOM -> generateSource(size)
        SourceType.SORTED -> generateSource(size).sorted()
        SourceType.SORTED_RIGHT -> generateSource(size).let {
            if(size < 3) it
            else it.subList(0, size/2) + it.subList(size/2 + 1, size-1).sorted()
        }
        SourceType.REVERSED -> generateSource(size).sorted().reversed()
        SourceType.EQUALS -> Array(size) { "value" }.toList()
    }
}
private fun toPrint(list: List<Any>): String {
    if(list.size < 16) return list.toString()
    return list.subList(0, 15).joinToString(prefix = "[", postfix = "...")
}
private fun toPrint(value: Long): String {
    for(power in 1..10) {
        if(value < 10.0.pow(power * 3)) {
            val result = mutableListOf<Char>()
            value.toString().toCharArray().reversed().forEachIndexed { index, item ->
                if(index != 0 && index % 3 == 0) result.add('_')
                result.add(item)
            }
            return result.reversed().joinToString(separator = "")
        }
    }
    return value.toString()
}
fun <T: Comparable<T>> sort(algorithm: SortingAlgorithm, source: List<T>) {
    println("""
        ---
        ${algorithm.name} ${algorithm.complexity}
        ${algorithm.complexity.explain}
    """.trimIndent())
    println("source: " + toPrint(source))
    val result = algorithm.sort(source)
    require(result.values == source.sorted())
    println("""
        time: ${result.timeInMillis}ms
        size: ${source.size}
        permutations: ${result.permutations}
        comparisons: ${toPrint(result.comparisons)} / ${toPrint(algorithm.complexity.expectedComparisons(source.size))}
        values: ${toPrint(result.values)}
    """.trimIndent())
}
fun sort(algorithms: Set<SortingAlgorithm>, size: Int, sourceType: SourceType) {
    val source = generateSource(size, sourceType)
    algorithms.forEach {
        sort(it, source)
    }
}

fun main() {
    val sourceType =
        SourceType.RANDOM
//        SourceType.SORTED
//        SourceType.SORTED_RIGHT
//        SourceType.REVERSED
//        SourceType.EQUALS
    sort(setOf(StupidSort), size = 1_000, sourceType = sourceType)
    sort(
        algorithms = setOf(
            BubbleSort,
            BubbleExSort,
            CocktailShakerSort
        ),
        size = 20_000, sourceType = sourceType
    )
}
