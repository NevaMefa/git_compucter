package Alishev;

import java.util.Scanner;

/**
 * Укороченная версия без дублежа
 * while снала проверяет услоие, а потом выполняет иструкции
 * do while сначала проеряет иструкии,  потом проеряет условия
 * гарантирует однократное выполнение написанного
 */
public class alishev8dowhile2 {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);
        int value;
        do {
            System.out.println("Введи 5");
            value = scanner.nextInt();
        } while (value !=5);
        System.out.println("Вы ввели 5");
    }
}
