package com.nevamefa.cs.lesson14;

public class lesson14Runner {

    public static void main(String[] args) {
       //нельз юзать boolean только граничный перечень типов
        // byte, short, int, char, String, enum
        // присвоили значение
        int month = 22;
        // к свичу значение
        switch (month) {
            // дальше варианты кейсов значений
            case 11:
                System.out.println("Autumn");
                break;
            case 2:
                System.out.println("Winter");
                break;
            case 3:
                System.out.println("spring");
                break;
                // дл вариантов не из кейсов пишут так:
            default:
                System.out.println("Month is invalid");


        }
    }
}
