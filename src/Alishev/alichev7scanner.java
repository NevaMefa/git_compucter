/**
 * пакеты
 * классы пакетов 
 * java.lang испортируются по умолчанию
 * java.util надо импортировать в ручную
 */


package Alishev;
import java.util.Scanner;
public class alichev7scanner {
    public static void main(String[] args) {
        // в классе, в параметрах нового объекта класса "входной поток"
        Scanner s = new Scanner(System.in);
        System.out.println("Введите что-нибудь");
        // новый объект класса и ссылаем на то, что будет получено с клавы (методом)
       // String string = s.nextLine();
        // либо числа, а не строка
        int x = s.nextInt();
        System.out.println("Вы ввели "+ x);
    }
}
