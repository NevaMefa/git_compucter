/**
 * Дано целое число. Определить, является ли последняя цифра жтого числа - цифрой 3
 */
package lesson1516;

public class task2 {
    public static void main(String[] args) {
        int value = 1233243;
        if (value % 10 == 3) {
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }
}
/**
 * 2 вариант решения
 * public class task2 {
 *     public static void main(String[] args) {
 *         int value = 1233243;
 *         int reminder = value % 10;
 *         boolean test == reminder == 3 || reminder == -3;
 *         if (test) {
 *             System.out.println("Yes");
 *         }else {
 *             System.out.println("No");
 *         }
 *     }
 * }
 */