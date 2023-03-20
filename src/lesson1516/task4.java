/**
 * Написать программу, которая по полученному году, определяет является ли код високосным.
 * Год высокосные, если он делится без остатка на 4. Однако, если год так же делится на 100,
 * то этот год не високосный, за исключением годов, делящихся на 400.
 * 1992 високосный
 * 1900 не високосный
 * 2000 високосный
 */

package lesson1516;

public class task4 {
    // [modifiers] например public static модификаторы функции
    // возвращающие значения void - ничего не возвращает, int, double, string  - возвращают.
    //void - ничего не возвращает ЭТО ИСКЛЮЧЕНИЕ
    // [modifiers] return_value nameVerb([param])
    public static void main(String[] args) {
        int year = 1900;
        System.out.println("високосный: " + isLeapYear(year));
    }
        //число делим на 4 и берем остаток от деления, в данном примере "0"
//        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
//            System.out.println(year + " високосный");
//        } else {
//            System.out.println(year + " не високосный");
//        }
    // пример 2
    public static boolean isLeapYear (int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
//            return true;
//        } else {
//            return false;
//        }
  }
}
