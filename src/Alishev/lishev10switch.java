package Alishev;

import java.util.Scanner;

/**
 * похож на if но удобен,когда оч много условий
 */
public class lishev10switch {
    public static void main(String[] args) {
        //для ввода данных с кклавиатуры пишем сканнер

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи возраст");
        // переменную можно добавить и строковую
        int age = scanner.nextInt();
        switch (age){
            //можно писать и несколько условий для одного вывода
            case 0:
            case 1:
                System.out.println("Ты родился");
                break;
            case 7:
                    System.out.println("Ты пошел в школу");
                    break;
            case 18:
                //можно не писать, наслучай если введен не подходящий вариант
                        System.out.println("Ты закончил школу");
                        break;
            default:
                System.out.println("Ни одно из предыдущих условий не подошло");

        }
    }
}
