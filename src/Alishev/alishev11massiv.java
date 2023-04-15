package Alishev;

/**
 *         // char character = 'a'; //примитивный тип данных
 *         // string s = "Hello"; // ссылочный тип данных
 *         // string s1 = new String("Hello"); // ссылочный тип данных
 *         // примитивные это коробки
 *         // ссылочные это те, что ссылаются
 *
 */
public class alishev11massiv {
    public static void main(String[] args) {
        int number = 10; //примитивный тип данных
        int []numbers = new int[5]; //ссылочный.Массив переменная ссылается на него. Массив создан/  в массиве 5 пустых ячеек
        for (int i =0; i<numbers.length; i++){
            numbers[i] = i*10;
        }
        for (int i = 0; i < numbers.length; i++){
            System.out.println(numbers[i]);
        }
    }
}
