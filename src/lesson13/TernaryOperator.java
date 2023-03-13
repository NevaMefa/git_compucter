package com.nevamefa.cs.lesson13;

public class TernaryOperator {

    public static void main(String[] args) {
        // максимальное значение из 2
        int value1 = 10;
        int value2 = 90;
        int max = value1 > value2
                ? value1 //true or false ?
                : value2; //иначе ":"
//        if (value1 > value2) {
//            max = value1;
//        } else {
//            max = value2;
//        }
        System.out.println("max value is: " + max);
    }
}
