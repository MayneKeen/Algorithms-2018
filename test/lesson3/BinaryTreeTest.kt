package lesson3

import org.junit.jupiter.api.Tag
import java.util.*
import kotlin.test.*

class BinaryTreeTest {
    private fun testAdd(create: () -> CheckableSortedSet<Int>) {
        val tree = create()
        tree.add(10)
        tree.add(5)
        tree.add(7)
        tree.add(10)
        assertEquals(3, tree.size)
        assertTrue(tree.contains(5))
        tree.add(3)
        tree.add(1)
        tree.add(3)
        tree.add(4)
        assertEquals(6, tree.size)
        assertFalse(tree.contains(8))
        tree.add(8)
        tree.add(15)
        tree.add(15)
        tree.add(20)
        assertEquals(9, tree.size)
        assertTrue(tree.contains(8))
        assertTrue(tree.checkInvariant())
        assertEquals(1, tree.first())
        assertEquals(20, tree.last())
    }

    @Test
    @Tag("Example")
    fun testAddKotlin() {
        testAdd { createKotlinTree() }
    }

    @Test
    @Tag("Example")
    fun testAddJava() {
        testAdd { createJavaTree() }
    }

    private fun <T : Comparable<T>> createJavaTree(): CheckableSortedSet<T> = BinaryTree()

    private fun <T : Comparable<T>> createKotlinTree(): CheckableSortedSet<T> = KtBinaryTree()

    private fun testRemove(create: () -> CheckableSortedSet<Int>) {
        val random = Random()
        for (iteration in 1..100) {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                list.add(random.nextInt(100))
            }
            val treeSet = TreeSet<Int>()
            val binarySet = create()
            for (element in list) {
                treeSet += element
                binarySet += element
            }
            val toRemove = list[random.nextInt(list.size)]
            treeSet.remove(toRemove)
            binarySet.remove(toRemove)
            println("Removing $toRemove from $list")
            assertEquals<SortedSet<*>>(treeSet, binarySet, "After removal of $toRemove from $list")
            assertEquals(treeSet.size, binarySet.size)
            for (element in list) {
                val inn = element != toRemove
                assertEquals(inn, element in binarySet,
                        "$element should be ${if (inn) "in" else "not in"} tree")
            }
            assertTrue(binarySet.checkInvariant())
        }

        //my tests
        //trying to remove an element from an empty tree
        val binSet = create()
        assertFalse(binSet.remove(16))
        assertEquals<Set<*>>(emptySet<Int>(), binSet)
        assertEquals(0, binSet.size)


        //trying to remove null from an empty tree
        assertFalse(binSet.remove(null))
        assertEquals(0, binSet.size)


        //trying to remove a nonexistent element
        binSet += 2
        binSet += 4
        binSet += 16
        binSet += 8
        binSet += 13
        binSet += 64
        assertFalse(binSet.remove(32))
        assertEquals<SortedSet<*>>(sortedSetOf(2, 4, 8, 13, 16, 64), binSet)
        assertEquals(6, binSet.size)


        //trying to remove null from a filled tree
        assertFalse(binSet.remove(null))
        assertEquals<SortedSet<*>>(sortedSetOf(2, 4, 8, 13, 16, 64), binSet)
        assertEquals(6, binSet.size)
    }

    @Test
    @Tag("Normal")
    fun testRemoveKotlin() {
        testRemove { createKotlinTree() }
    }

    @Test
    @Tag("Normal")
    fun testRemoveJava() {
        testRemove { createJavaTree() }
    }

    private fun testIterator(create: () -> CheckableSortedSet<Int>) {
        val random = Random()
        for (iteration in 1..100) {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                list.add(random.nextInt(100))
            }
            val treeSet = TreeSet<Int>()
            val binarySet = create()
            for (element in list) {
                treeSet += element
                binarySet += element
            }
            val treeIt = treeSet.iterator()
            val binaryIt = binarySet.iterator()
            println("Traversing $list")
            while (treeIt.hasNext()) {
                assertEquals(treeIt.next(), binaryIt.next())
            }
        }

        //my tests
        val testList = mutableListOf<Int>()
        for (i in 0 until 16) {
            testList.add(16)
        }

        val treeSet = TreeSet<Int>()
        val binSet = create()
        for (element in testList) {
            treeSet += element
            binSet += element
        }
        val treeIterator = treeSet.iterator()
        val binIterator = binSet.iterator()
        println("Traversing $testList")
        while (treeIterator.hasNext()) {
            assertEquals(treeIterator.next(), binIterator.next())
        }

        for (i in 1..100) {
            testList.add(random.nextInt(100))
        }
        for (element in testList) {
            treeSet += element
            binSet += element
        }
        println("Traversing $testList")
        while (treeIterator.hasNext()) {
            assertEquals(treeIterator.next(), binIterator.next())
        }


        //iterator of an empty tree
        binSet.clear()

        assertFalse(binIterator.hasNext())
        try {
            binIterator.next()
        } catch (e: NoSuchElementException) {
            println("caught a NoSuchElementException - empty tree")
        }


        //out of bounds

        binSet += 2
        binSet += 1
        binSet += 16
        binSet += 8
        binSet += 19
        binSet += 20
        binSet += 3

        try {
            binIterator.next()
        } catch (e: NoSuchElementException) {
            println("caught a NoSuchElementException - out of bounds")
        }
    }


    @Test
    @Tag("Normal")
    fun testIteratorKotlin() {
        testIterator { createKotlinTree() }
    }

    @Test
    @Tag("Normal")
    fun testIteratorJava() {
        testIterator { createJavaTree() }
    }

    private fun testIteratorRemove(create: () -> CheckableSortedSet<Int>) {
        val random = Random()
        for (iteration in 1..100) {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                list.add(random.nextInt(100))
            }
            val treeSet = TreeSet<Int>()
            val binarySet = create()
            for (element in list) {
                treeSet += element
                binarySet += element
            }
            val toRemove = list[random.nextInt(list.size)]
            treeSet.remove(toRemove)
            println("Removing $toRemove from $list")
            val iterator = binarySet.iterator()
            while (iterator.hasNext()) {
                val element = iterator.next()
                print("$element ")
                if (element == toRemove) {
                    iterator.remove()
                }
            }
            println()
            assertEquals<SortedSet<*>>(treeSet, binarySet, "After removal of $toRemove from $list")
            assertEquals(treeSet.size, binarySet.size)
            for (element in list) {
                val inn = element != toRemove
                assertEquals(inn, element in binarySet,
                        "$element should be ${if (inn) "in" else "not in"} tree")
            }
            assertTrue(binarySet.checkInvariant())
        }


        //my tests

        //empty tree

        val binSet = create()
        val binIterator = binSet.iterator()
        try {
            binIterator.remove()
        } catch (e: NoSuchElementException) {
            println("caught a NoSuchElementException - empty tree itRemove")
        }


        //trying to remove the last element in the tree

        binSet += 1
        val bIterator = binSet.iterator()
        bIterator.next()
        bIterator.remove()

        try {
            bIterator.next()
        } catch (e: NoSuchElementException) {
            println("caught a NoSuchElementException - removing last element 1")
        }
        try {
            bIterator.remove()
        } catch (e: NoSuchElementException) {
            println("caught a NoSuchElementException - removing last element 2")
        }


    }

    @Test
    @Tag("Hard")
    fun testIteratorRemoveKotlin() {
        testIteratorRemove { createKotlinTree() }
    }

    @Test
    @Tag("Hard")
    fun testIteratorRemoveJava() {
        testIteratorRemove { createJavaTree() }
    }
}