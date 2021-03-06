package lesson1;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import kotlin.NotImplementedError;

import java.io.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     *
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */

    //Трудоемкость T = O(n*log(n))
    //Ресурсоемкость R = O(n)

    static public void sortTimes(String inputName, String outputName) throws IOException{
        Scanner scanner = new Scanner(new FileReader(inputName));
        FileWriter fileWriter = new FileWriter(outputName);

        List<String> times=new ArrayList<>();

        while (scanner.hasNextLine())
            times.add(scanner.nextLine());
        scanner.close();

        Collections.sort(times, Comparator.naturalOrder());

        for(int i = 0; i<times.size(); i++) {
            fileWriter.write(times.get(i));
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    //Трудоемкость T = O(n^2*log(n))
    //Ресурсоемкость R = O(n^2)



    public static void adder(Map<String, List<String>> map, String key, String value) {
        if (map.get(key) != null) {
            map.get(key).add(value);
        }
        else {
            List<String> temp = new ArrayList<>();
            temp.add(value);
            map.put(key, temp);
        }
    }

    static public void sortAddresses(String inputName, String outputName) throws IOException{

        Scanner scanner = new Scanner((new FileReader(inputName)));
        List<String> adresses = new ArrayList<>();

        while (scanner.hasNextLine())                                              //Иванов Михаил - Железнодорожная 7
            adresses.add(scanner.nextLine());                                      //Железнодорожная 7 - Иванов Алексей, Иванов Михаил
        scanner.close();

        List<String[]> splitted = new ArrayList<>();
        Map<String, List<String>> map = new TreeMap<>();


        for(int i=0; i<adresses.size(); i++) {
            String[] temp = adresses.get(i).split(" - ");

            adder(map, temp[1], temp[0]);
        }

        FileWriter fileWriter = new FileWriter(outputName);

        for(String element: map.keySet()) {
            List<String> tmp = map.get(element);
            Collections.sort(tmp);
            String out = tmp.toString();
            String result = out.substring(1, out.length()-1);

            fileWriter.write(element);
            fileWriter.write(" - ");
            fileWriter.write(result);
            fileWriter.write("\n");
        }
        fileWriter.close();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    //Трудоемкость T = O(n*log(n))
    //Ресурсоемкость R = O(n)

    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        Scanner scanner = new Scanner(new FileReader(inputName));
        FileWriter fileWriter = new FileWriter(outputName);
        List<Double> numbers = new ArrayList<>();

        while (scanner.hasNextLine()) {
            numbers.add(Double.parseDouble(scanner.nextLine()));
        }
        scanner.close();
        Collections.sort(numbers);

        for(int i = 0; i<numbers.size(); i++) {
            fileWriter.write(numbers.get(i) + "\n");
        }

        fileWriter.close();
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    //Трудоемкость T = O(n)
    //Ресурсоемкость R = O(n)

    static public void sortSequence(String inputName, String outputName) throws IOException {
        Scanner scanner = new Scanner(new FileReader(inputName));
        FileWriter fileWriter = new FileWriter(outputName);

        Map<Integer, Integer> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            Integer currentNum = Integer.parseInt(scanner.nextLine());

            if(map.containsKey(currentNum)) {
                Integer a = map.get(currentNum) + 1;
                map.put(currentNum, a);
            }
            else {
                map.put(currentNum, 1);
            }
        }
        scanner.close();

        Integer maxKey = 0;
        Integer maxVal = 0;


        for(Integer element: map.keySet()) {
            Integer a = map.get(element);

            if(a > maxVal){
                maxVal = a;
                maxKey = element;
            }
        }

        Scanner scanner2 = new Scanner(new FileReader(inputName));
        while (scanner2.hasNextLine()) {
            Integer a = Integer.parseInt(scanner2.nextLine());
            if(!a.equals(maxKey)) {
                fileWriter.write(a + "\n");
            }
        }
        scanner2.close();

        for(int i = 0; i<maxVal; i++) {
            fileWriter.write(maxKey +"\n");
        }
        fileWriter.close();

    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    //Трудоемкость T = O(n*log(n))
    //Ресурсоемкость R = O(n) , где n = first.length + second.length

    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        System.arraycopy(first, 0, second, 0, first.length);
        Arrays.sort(second);
    }
}
