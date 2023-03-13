package com.nevamefa.cs.lesson13;

public class Lesson13Runner {

    public static void main(String[] args) {
        int value = 0;
//        boolean test = value > 0;
        // если больше 0 то:
        if (value > 0) {
            System.out.println(value + " Positive value");
            // если меньше 0 то:
        } else if (value < 0) {
            System.out.println(value + " Is negative");
            // иначе покажи:
        } else {
            System.out.println(" Zero");
        }
    }
}
