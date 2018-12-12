@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson5

import java.util.*
import kotlin.collections.ArrayList
import java.util.HashSet




/**
 * Эйлеров цикл.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
 * Если в графе нет Эйлеровых циклов, вернуть пустой список.
 * Соседние дуги в списке-результате должны быть инцидентны друг другу,
 * а первая дуга в списке инцидентна последней.
 * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
 * Веса дуг никак не учитываются.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
 *
 * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
 * связного графа ровно по одному разу
 */


fun Graph.findEulerLoop(): List<Graph.Edge> {
    TODO()
}

/**
 * Минимальное остовное дерево.
 * Средняя
 *
 * Дан граф (получатель). Найти по нему минимальное остовное дерево.
 * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
 * вернуть любое из них. Веса дуг не учитывать.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ:
 *
 *      G    H
 *      |    |
 * A -- B -- C -- D
 * |    |    |
 * E    F    I
 * |
 * J ------------ K
 */
fun Graph.minimumSpanningTree(): Graph {
    TODO()
}

/**
 * Максимальное независимое множество вершин в графе без циклов.
 * Сложная
 *
 * Дан граф без циклов (получатель), например
 *
 *      G -- H -- J
 *      |
 * A -- B -- D
 * |         |
 * C -- F    I
 * |
 * E
 *
 * Найти в нём самое большое независимое множество вершин и вернуть его.
 * Никакая пара вершин в независимом множестве не должна быть связана ребром.
 *
 * Если самых больших множеств несколько, приоритет имеет то из них,
 * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
 *
 * В данном случае ответ (A, E, F, D, G, J)
 *
 * Эта задача может быть зачтена за пятый и шестой урок одновременно
 */

//n = graph.vertices.size
//Ресурсоемкость R = O(n)
//Трудоемкость T = O(n)
//т.к. в худшем случае пройдем по всем вершинам несколько раз
fun Graph.largestIndependentVertexSet(): Set<Graph.Vertex> {
    var graph = this

    if (graph.vertices.isEmpty()) {
        return emptySet()
    }
    if (graph.edges.isEmpty()) return graph.vertices

    val result = HashSet<Graph.Vertex>()
    val invResult = HashSet<Graph.Vertex>()

    val edges = graph.edges

    var first: Graph.Edge? = null

    for (edge in edges) {
        val eBegin = edge.begin
        val eEnd = edge.end

        if (result.isEmpty() || eBegin === first!!.begin) first = edge
        if (!result.contains(first!!.end)) {
            result.add(first.end)
            invResult.add(first.begin)
        }
        if (result.contains(eBegin)) invResult.add(eEnd)
        if (invResult.contains(eBegin)) result.add(eEnd)
    }
    return if (result.size > invResult.size)
        result
    else
        invResult

}

/**
 * Наидлиннейший простой путь.
 * Сложная
 *
 * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
 * Простым считается путь, вершины в котором не повторяются.
 * Если таких путей несколько, вернуть любой из них.
 *
 * Пример:
 *
 *      G -- H
 *      |    |
 * A -- B -- C -- D
 * |    |    |    |
 * E    F -- I    |
 * |              |
 * J ------------ K
 *
 * Ответ: A, E, J, K, D, C, H, G, B, F, I
 */
//Трудоемкость T = O(v)
//Ресурсоемкость R = O(v)
fun Graph.longestSimplePath(): Path {

    val graph = this

    var vertices = graph.vertices
    val first = vertices.iterator().next()
    var longest = Path(first)

    var deque = ArrayDeque<Path>()

    for (vertex in vertices) {
        deque.add(Path(vertex))
    }

    while (!deque.isEmpty()) {
        var currentPath = deque.pop()
        var vs = currentPath.vertices

        if (currentPath.length > longest.length) {
            longest = currentPath
            if (vs.size > vertices.size) break
        }

        var neighbours = graph.getNeighbors(vs[vs.size - 1])

        for (neighbour in neighbours) {
            if (currentPath.contains(neighbour)) {
                continue
            } else {
                deque.push(Path(currentPath, graph, neighbour))
            }
        }
    }
    return longest
}
