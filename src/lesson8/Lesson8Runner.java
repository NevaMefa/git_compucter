package com.nevamefa.cs.lesson8;

public class Lesson8Runner {

    public static void main(String[] args) {
        // варианты присвоения числа к переменным,
        // либо сразу после названия,
        // либо после присвоения названия
        double doubleValue = 12.98;
        System.out.println(doubleValue);

        int intValue; //название для переменной (переменная_название;)
        intValue = 28; //initialization присвоили нашей переменной число
        System.out.println(intValue); //int


        // можно подставлять одному значнию другое
        int intValue2 = 1988;
        intValue = intValue2;

        intValue = 67;
        System.out.println(intValue);

        // final запрещает менять значение переменной
        final byte byteValue = 39; //тк байт не имеет литерала (-128 до +127)
        System.out.println(byteValue);
       // byteValue = -89;

        char charValue = 'q';
        System.out.println(charValue);

        boolean booleanValue = true;
        System.out.println(booleanValue);

        String stringValue = "Hello world"; //строка с большой буквы и
        // не является ключевым словом
        System.out.println(stringValue);


    }
}
