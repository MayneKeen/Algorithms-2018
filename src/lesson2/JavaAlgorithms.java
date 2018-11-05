package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;
import org.intellij.lang.annotations.Language;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw  new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     */
    //Трудоемкость: T = O(n)
    //Ресурсоемкость: R = О(1)
    static public int josephTask(int menNumber, int choiceInterval) throws Exception{
        if (menNumber == 0)
            throw new Exception("Некорректный формат файла");
        int a = 0;
        for (int i=0; i<menNumber; i++)
        a = (a + choiceInterval) % (i + 1);
        return ++a;
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */

    //Трудоемкость: T = O(n^2)
    //Ресурсоемкость: R = О(n), где n = first.length()*second.length()
    static public String longestCommonSubstring(String first, String second) {

        if (first == null || second == null) {
            return "";
        }

        int fl = first.length(), sl = second.length();

        int lcs[][] = new int[fl + 1][sl + 1];

        int len = 0;
        int row = 0;
        int col = 0;

        for (int i = 0; i <= fl; i++) {
            for (int j = 0; j <= sl; j++) {
                if (i == 0 || j == 0)
                    lcs[i][j] = 0;

                else if (first.charAt(i - 1) == second.charAt(j - 1)) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                    if (len < lcs[i][j]) {
                        len = lcs[i][j];
                        row = i;
                        col = j;
                    }
                } else
                    lcs[i][j] = 0;
            }
        }
        if (len == 0)
            return "";

        String resultStr = "";

        while (lcs[row][col] != 0) {
            resultStr = first.charAt(row - 1) + resultStr;
            --len;
            row--;
            col--;
        }

        return resultStr;
    }


    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */

    /*
    public static char[][] toMatrix(List<String> list) {
        row = list.size();
        col = list.get(0).length() / 2 + 1;
        char[][] matrix = new char[row][col];

        int i = 0;
        for (String element : list) {

            for (int j = 0; j < element.length(); j += 2) {
                System.out.println(row +" " + col + " " + i + " " + j );
                matrix[i][j / 2] = element.charAt(j);
            }
            i++;

        }
        return matrix;
    }


    static int row,
            col ;
    static String word = "";
    static Set<String> result;
    static List<Character> wordLetters = new ArrayList<>();


    private static void baldaHelper(int w, int l) {
        int cr = 0;
        int pos = col;
        int r = 1;

        if (w != word.length()) {
            for (int i =1; i < row; i++) {
                if (l >= pos) {
                    pos += col;
                    r++;
                } else
                    cr = r;
            }

            if ((l < ((cr * col) - 1)) && (wordLetters.get(l + 1) == word.charAt(w)))
                baldaHelper(w + 1, l + 1);


            if ((l > ((cr - 1) * col)) && (wordLetters.get(l - 1) == word.charAt(w)))
                baldaHelper(w + 1, l - 1);


            if ((l < (wordLetters.size() - col - 1)) && (wordLetters.get(l + col) == word.charAt(w)))
                baldaHelper(w + 1, l + col);


            if ((l > (col - 1)) && (wordLetters.get(1 - col) == word.charAt(w)))
                baldaHelper(w + 1, l - col);
        } else {
            result.add(word);
        }


    }

*/


    static public Set<String> baldaSearcher(String inputName, Set<String> words) /*throws IOException*/ {
        throw new NotImplementedError();
    }

    /*
        Scanner scanner = new Scanner(new FileReader(inputName));
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }

        scanner.close();
        char[][] matrix = toMatrix(list);

       /* Map<Pair<Integer, Integer>, Character> map = new HashMap<>();

        for(int i=0; i<row; i++)
            for (int j=0; j<col; j++)
                map.put(new Pair<>(i, j), matrix[i][j]);


        for(String element: words) {
            int i=0;
            int j=0;


            i++;


        for (String element : words) {
            word = element;
            char flet = word.charAt(0);
            for (int i=0; i<wordLetters.size(); i++)
                if (flet == wordLetters.get(i))
                    baldaHelper(1, i);
        }

        return result;
    }






        List<Pair<Integer, Integer>> usedLetters = new ArrayList<>();
        List<Pair<Integer, Integer>> temp = new ArrayList<>();

        for(String element:words) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (!(baldaHelper(element, )))
                }
            }
        }



*/
}