/**
 * Имеется целое число. Это число - сумма в рублях. Вывести число, добавив к нему слово "рубль" в правильном падеже
 */


package lesson1516;

public class task3 {
    public static void main(String[] args) {
        int rubles = 1012;
        // рубль 1, !11
        // рубля 2,3,4, !12-14
        // рублей 0,5-9,11-19
        int reminder10 = rubles % 10;
        int reminder100 = rubles % 100;
        if (reminder10 == 0 || (5<= reminder10 && reminder10 <=9) || (11 <= reminder100 && reminder100 <= 19)) {
            System.out.println(rubles + " Рублей");
        } else if (2 <= reminder10 && reminder10 <= 4){
            System.out.println(rubles + " Рубля");
        } else if (reminder10 == 1) {
            System.out.println(rubles + " Рубль");
        }else {
            System.out.println("Rubles variable is invalid: " + rubles);
        }
    }
}
