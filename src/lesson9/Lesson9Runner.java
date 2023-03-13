package com.nevamefa.cs.lesson9;

public class Lesson9Runner {

    public static void main(String[] args) {
        //неочевидное приведение к вечещственному
        int value = 10;
        System.out.println(value / (double) 4);
        // или очевидное приведение к вечещственному
//        double value = 10;
//        System.out.println(value / 4);
        //результат всегда будет ЦЕЛЫМ числом
//        int value = 10;
//        System.out.println(value / 4);
        // что бы вывести десятичное надо сделать одно число вечественным.
        // либо 4.0 , либо int сделать double

//        int value = 10;
//        value = value + 1; //либо
//        value += 1; // унарная операция или операция инкремента
//        value++; // все варианты увеличивают значение value на 1
//        // операция декремента
//        value--; // value - 1; value -= 1;
//        System.out.println(value);


        //       int value = 10;
        //       value = (value + 5) / 3;//получается значение 10 + 5 илли другой пример
        //        System.out.println(value);

// +,-,*,/,% (деление по модулю, статок от деления)
//        int first = 10;
//        int second = 4;
//        int value = first % second;
//        System.out.println(value);
    }
}
