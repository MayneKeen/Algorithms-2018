package lesson3

import java.util.SortedSet
import kotlin.NoSuchElementException

// Attention: comparable supported but comparator is not
class KtBinaryTree<T : Comparable<T>> : AbstractMutableSet<T>(), CheckableSortedSet<T> {

    private var root: Node<T>? = null

    override var size = 0
        private set

    private class Node<T>(val value: T) {

        var left: Node<T>? = null

        var right: Node<T>? = null
    }

    override fun add(element: T): Boolean {
        val closest = find(element)
        val comparison = if (closest == null) -1 else element.compareTo(closest.value)
        if (comparison == 0) {
            return false
        }
        val newNode = Node(element)
        when {
            closest == null -> root = newNode
            comparison < 0 -> {
                assert(closest.left == null)
                closest.left = newNode
            }
            else -> {
                assert(closest.right == null)
                closest.right = newNode
            }
        }
        size++
        return true
    }

    override fun checkInvariant(): Boolean =
            root?.let { checkInvariant(it) } ?: true

    private fun checkInvariant(node: Node<T>): Boolean {
        val left = node.left
        if (left != null && (left.value >= node.value || !checkInvariant(left))) return false
        val right = node.right
        return right == null || right.value > node.value && checkInvariant(right)
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */


    private fun findParent(value: T): Node<T>? {
        var current = root
        var parent = root

        while (current != null) {
            val comparison = current.value.compareTo(value)

            if (comparison > 0) {

                parent = current
                current = current.left
            } else if (comparison < 0) {

                parent = current
                current = current.right
            } else
                break
        }
        return if (parent == root) null else parent
    }


    override fun remove(element: T): Boolean {
        val closest = find(element) ?: return false
        val current = (if (element.compareTo(closest.value) == 0) closest else null) ?: return false

        size--
        if (current == root) {
            root = null
            return true
        }

        val parent = findParent(current.value)

        if (current.right == null) {
            if (parent == null) {
                root = current.left
            } else {
                val comparison = parent.value.compareTo(current.value)

                if (comparison > 0) {
                    parent.left = current.left
                } else if (comparison < 0) {
                    parent.right = current.left
                }
            }
        } else if (current.right!!.left == null) {
            current.right!!.left = current.left
            if (parent == null) {
                root = current.right
            } else {
                val comparison = parent.value.compareTo(current.value)
                if (comparison > 0) {
                    parent.left = current.right
                } else if (comparison < 0) {
                    parent.right = current.right
                }
            }
        } else {
            var leftmost = current.right!!.left
            var leftmostParent = current.right
            while (leftmost!!.left != null) {
                leftmostParent = leftmost
                leftmost = leftmost.left

            }
            leftmostParent!!.left = leftmost.right
            leftmost.left = current.left
            leftmost.right = current.right
            if (parent == null) {
                root = leftmost
            } else {
                val comparison = parent.value.compareTo(current.value)
                if (comparison > 0) {
                    parent.left = leftmost
                } else if (comparison < 0) {
                    parent.right = leftmost
                }
            }
        }
        return true

    }

    override operator fun contains(element: T): Boolean {
        val closest = find(element)
        return closest != null && element.compareTo(closest.value) == 0
    }

    private fun find(value: T): Node<T>? =
            root?.let { find(it, value) }

    private fun find(start: Node<T>, value: T): Node<T> {
        val comparison = value.compareTo(start.value)
        return when {
            comparison == 0 -> start
            comparison < 0 -> start.left?.let { find(it, value) } ?: start
            else -> start.right?.let { find(it, value) } ?: start
        }
    }

    inner class BinaryTreeIterator : MutableIterator<T> {

        private var current: Node<T>? = null

        /**
         * Поиск следующего элемента
         * Средняя
         */
        private fun findNext(): Node<T>? {

            TODO()
        }

        override fun hasNext(): Boolean = findNext() != null

        override fun next(): T {
            current = findNext()
            return (current ?: throw NoSuchElementException()).value
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        override fun remove() {
            TODO()
        }
    }

    override fun iterator(): MutableIterator<T> = BinaryTreeIterator()

    override fun comparator(): Comparator<in T>? = null

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    override fun subSet(fromElement: T, toElement: T): SortedSet<T> {
        TODO()
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    override fun headSet(toElement: T): SortedSet<T> {
        TODO()
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    override fun tailSet(fromElement: T): SortedSet<T> {
        TODO()
    }

    override fun first(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.left != null) {
            current = current.left!!
        }
        return current.value
    }

    override fun last(): T {
        var current: Node<T> = root ?: throw NoSuchElementException()
        while (current.right != null) {
            current = current.right!!
        }
        return current.value
    }
}
