@file:Suppress("UNUSED_PARAMETER")

package lesson6

import java.io.File
import java.io.FileReader
import java.io.IOException
import java.util.*

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {
    TODO()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    TODO()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
@Throws(IOException::class)
internal fun lH(inputName: String): IntArray {
    val scanner = Scanner(FileReader(inputName))
    val list = ArrayList<String>()
    val result = IntArray(2)
    val length: Int
    val height: Int

    while (scanner.hasNextLine())
        list.add(scanner.nextLine())
    scanner.close()
    result[1] = list.size                 //length
    result[0] = list[0].length / 2 + 1   //height
    return result
}


@Throws(IOException::class)
internal fun readField(inputName: String, length: Int, height: Int): Array<IntArray> {
    val result: Array<IntArray> = Array(length) { IntArray(height) }
    val scanner = Scanner(FileReader(inputName))
    val list = ArrayList<String>()

    while (scanner.hasNextLine())
        list.add(scanner.nextLine())
    scanner.close()

    for (i in 0 until length) {

        val temp = list[i]
        val arr = temp.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (j in 0 until height) {
            result[i][j] = Integer.parseInt(arr[j])
        }
    }

    return result
}

internal fun recFunction(i: Int, j: Int, f: Array<IntArray>): Int {
    if (i == 0 && j == 0) {
        return f[0][0]
    }
    if (j == 0 && i != 0) {
        return recFunction(i - 1, 0, f) + f[i][0]
    }
    if (i == 0 && j != 0) {
        return recFunction(0, j - 1, f) + f[0][j]
    }
    return if ((i != 0) and (j != 0)) {
        Math.min(Math.min(recFunction(i - 1, j - 1, f), recFunction(i - 1, j, f)),
                recFunction(i, j - 1, f)) + f[i][j]
    } else 1

}


//      Изначально написал на java, т.к. в котлине запутался в синтаксисе, связанном
//     с двумерными массивами (в методе readField), копипастнул в DynamicTasks.kt, idea код сама перевела
//     не бейте, если вырвиглазный код получился

@Throws(IOException::class)
fun shortestPathOnField(inputName: String): Int {
    val f: Array<IntArray>
    val length: Int
    val height: Int
    val temp = lH(inputName)
    length = temp[1]
    height = temp[0]

    f = readField(inputName, length, height)

    print(f[length - 1][height - 1])

    return recFunction(length - 1, height - 1, f)
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5