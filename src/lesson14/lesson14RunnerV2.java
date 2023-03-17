package com.nevamefa.cs.lesson14;

public class lesson14RunnerV2 {
    public static void main(String[] args) {
        // второй вариант когда кейсы пишутся подряд
        // для одного вывода
        int month = 22;
        switch (month) {
            case 12:
            case 1:
            case 2:
                System.out.println("winter");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("Autumn");
                break;
            default:
                System.out.println("You loser");
        }
    }
}
