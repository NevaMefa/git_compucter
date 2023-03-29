/**
 * работает до тех пор пока
 * не введут нужно значение
 *
 */
package Alishev;

import java.util.Scanner;

public class alishev8dowhile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // программа просит ести число
        System.out.println("Введите 5");
        // оно присваивается переменной
        int value = scanner.nextInt();
        while (value != 5) {
            // если введенное так и не равно условию то
            System.out.println("Введите 5");
            value = scanner.nextInt();
        }
// если равно условию то:
        System.out.println("Вы ввели 5");
    }
}
